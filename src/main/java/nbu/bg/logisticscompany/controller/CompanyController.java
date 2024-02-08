package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.annotation.security.isAdmin;
import nbu.bg.logisticscompany.exceptions.CompanyAlreadyExistsException;
import nbu.bg.logisticscompany.exceptions.CompanyNotFoundException;
import nbu.bg.logisticscompany.model.dto.CompanyDto;
import nbu.bg.logisticscompany.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * The type Company controller.
 */
@Controller
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    /**
     * Update company data string.
     *
     * @param companyDto the company dto
     * @param result     the result
     * @return the string
     */
//SEND DATA TO CHANGE IN COMPANY EDIT FORM WITH PUT
    @PutMapping("/company/edit")
    @isAdmin
    public String updateCompanyData(@ModelAttribute("company") @Valid CompanyDto companyDto, BindingResult result)
    {
        //IF THERE IS NO COMPANY IN THE DB TO EDIT THE USER IS REDIRECTED TO THE ADMIN PANEL
        if (!companyService.dbHasCompany())
        {
            return "redirect:/admin";
        }

        //IF THERE ARE ANY ERRORS THE EDIT COMPANY FORM IS RELOADED
        if (result.hasErrors())
        {
            return "edit-company";
        }

        //THE COMPANY CHANGES ARE ATTEMPTED TO BE SAVED AND IF THERE ARE ANY ERRORS A EXCEPTION IS THROWN
        try
        {
            companyService.updateCompany(companyDto.getId(), companyDto);

            return "redirect:/admin";
        }
        catch (IllegalArgumentException | CompanyNotFoundException e)
        {
            result.rejectValue("id", null, e.getMessage());

            return "edit-company";
        }
    }

    /**
     * Create new company string.
     *
     * @param companyDto the company dto
     * @param result     the result
     * @return the string
     */
//SEND DATA IN COMPANY CREATE FORM WITH POST
    @PostMapping("/company/create")
    @isAdmin
    public String createNewCompany(@ModelAttribute("company") @Valid CompanyDto companyDto, BindingResult result)
    {
        //IF THERE IS COMPANY IN THE DB THE USER IS REDIRECTED TO THE ADMIN PANEL
        if (companyService.dbHasCompany())
        {
            return "redirect:/admin";
        }

        if (result.hasErrors())
        {
            return "create-company";
        }

        //IF THERE ARE ANY ERRORS THE CREATE COMPANY FORM IS RELOADED
        try
        {
            companyService.createCompany(companyDto);

            return "redirect:/admin";
        }
        catch (CompanyAlreadyExistsException e)
        {
            result.rejectValue("name", "error.company", e.getMessage());

            return "create-company";
        }
    }

    /**
     * Delete company string.
     *
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
//DELETE ENTIRE DB SINCE THERE IS ONLY ONE COMPANY IN THE DATABASE
    @PostMapping("/company/delete")
    @isAdmin
    public String deleteCompany(RedirectAttributes redirectAttributes)
    {
        //IF THERE IS NO COMPANY IN THE DB THE USER IS REDIRECTED TO THE ADMIN PANEL
        if (!companyService.dbHasCompany())
        {
            return "redirect:/admin";
        }

        //THE COMPANY IS ATTEMPTED TO BE DELETED AND
        try
        {
            companyService.deleteCompany();

            redirectAttributes.addFlashAttribute("successMessage", "Company deleted successfully.");

        } catch (CompanyNotFoundException e)
        {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/admin";
    }

}
