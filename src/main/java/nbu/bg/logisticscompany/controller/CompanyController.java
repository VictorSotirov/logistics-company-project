package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.exceptions.CompanyAlreadyExistsException;
import nbu.bg.logisticscompany.exceptions.CompanyNotFoundException;
import nbu.bg.logisticscompany.model.dto.CompanyDto;
import nbu.bg.logisticscompany.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class CompanyController
{
    private final CompanyService companyService;

    //SEND DATA IN COMPANY EDIT FORM WITH PUT
    @PutMapping("/company/edit")
    public String updateCompanyData(@ModelAttribute("company") @Valid CompanyDto companyDto, BindingResult result)
    {
        if (!companyService.dbHasCompany())
        {
            return "redirect:/company";
        }

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

    //SEND DATA IN COMPANY CREATE FORM WITH POST
    @PostMapping("/company/create")
    public String createNewCompany(@ModelAttribute("company") @Valid CompanyDto companyDto, BindingResult result)
    {
        if (companyService.dbHasCompany())
        {
            return "redirect:/company";
        }

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

    //DELETE ENTIRE DB
    @PostMapping("/company/delete")
    public String deleteCompany(RedirectAttributes redirectAttributes )
    {
        if (!companyService.dbHasCompany())
        {
            return "redirect:/company";
        }

        try
        {
            companyService.deleteCompany();

            redirectAttributes.addFlashAttribute("successMessage", "Company deleted successfully.");
        }
        catch (CompanyNotFoundException e)
        {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/company";
    }

}
