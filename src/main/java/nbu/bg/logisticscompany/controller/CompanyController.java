package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.exceptions.CompanyAlreadyExistsException;
import nbu.bg.logisticscompany.exceptions.CompanyNotFoundException;
import nbu.bg.logisticscompany.model.dto.CompanyDto;
import nbu.bg.logisticscompany.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CompanyController
{
    private final CompanyService companyService;

    //GET COMPANY DATA PAGE
    @GetMapping("/company")
    public String showCompanyData(Model model)
    {
        Optional<CompanyDto> companyDtoOptional = companyService.getCompanyData();

        companyDtoOptional.ifPresent(companyDto -> model.addAttribute("company", companyDto));

        return "company";
    }

    //GET COMPANY EDIT FORM
    @GetMapping("/company/edit")
    public String showCompanyEditForm(Model model)
    {
        Optional<CompanyDto> companyDtoOptional = companyService.getCompanyData();

        if (companyDtoOptional.isPresent())
        {
            CompanyDto companyDto = new CompanyDto();

            companyDto.setId(companyDtoOptional.get().getId());

            model.addAttribute("company", companyDto);
        }

        return "edit-company";
    }

    //SEND DATA IN COMPANY EDIT FORM WITH PUT
    @PutMapping("/company/edit")
    public String updateCompanyData(@ModelAttribute("company") @Valid CompanyDto companyDto, BindingResult result)
    {
        if (result.hasErrors())
        {
            return "edit-company";
        }

        try
        {
            companyService.updateCompany(companyDto.getId(), companyDto);

            return "redirect:/company";
        }
        catch (IllegalArgumentException | CompanyNotFoundException e)
        {
            result.rejectValue("id", null, e.getMessage());

            return "edit-company";
        }
    }

    //GET COMPANY CREATE FORM
    @GetMapping("/company/create")
    public String showCompanyCreateForm(Model model)
    {
        model.addAttribute("company", new CompanyDto());

        return "create-company";
    }

    //SEND DATA IN COMPANY CREATE FORM WITH POST
    @PostMapping("/company/create")
    public String createNewCompany(@ModelAttribute("company") @Valid CompanyDto companyDto, BindingResult result)
    {
        if (result.hasErrors())
        {
            return "create-company";
        }

        try
        {
            companyService.createCompany(companyDto);

            return "redirect:/company";
        }
        catch (CompanyAlreadyExistsException e)
        {
            // Handle company already exists exception
            result.rejectValue("name", "error.company", e.getMessage());

            return "create-company";
        }
    }

    @PostMapping("/company/delete")
    public String deleteCompany(RedirectAttributes redirectAttributes )
    {
        try
        {
            companyService.deleteCompany();

            redirectAttributes.addFlashAttribute("successMessage", "Company deleted successfully.");
        }
        catch (CompanyNotFoundException e)
        {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        //ADD ADEQUATE REDIRECT
        return "redirect:/home";
    }
}
