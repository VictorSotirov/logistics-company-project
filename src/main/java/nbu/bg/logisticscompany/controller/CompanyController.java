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

import javax.validation.Valid;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CompanyController
{
    private final CompanyService companyService;

    @GetMapping("/company")
    public String showCompanyData(Model model)
    {
        Optional<CompanyDto> companyDtoOptional = companyService.getCompanyData();

        companyDtoOptional.ifPresent(companyDto -> model.addAttribute("company", companyDto));

        return "company";
    }

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

    @GetMapping("/company/create")
    public String showCompanyCreateForm(Model model)
    {
        model.addAttribute("company", new CompanyDto());

        return "create-company";
    }

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


}
