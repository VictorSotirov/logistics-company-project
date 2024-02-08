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
    public String updateCompanyData(@ModelAttribute("company") @Valid CompanyDto companyDto, BindingResult result) {
        if (!companyService.dbHasCompany()) {
            return "redirect:/company";
        }

        if (result.hasErrors()) {
            return "edit-company";
        }

        try {
            companyService.updateCompany(companyDto.getId(), companyDto);

            return "redirect:/admin";
        } catch (IllegalArgumentException | CompanyNotFoundException e) {
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
    public String createNewCompany(@ModelAttribute("company") @Valid CompanyDto companyDto, BindingResult result) {
        if (companyService.dbHasCompany()) {
            return "redirect:/admin";
        }

        if (result.hasErrors()) {
            return "create-company";
        }

        try {
            companyService.createCompany(companyDto);

            return "redirect:/admin";
        } catch (CompanyAlreadyExistsException e) {
            // Handle company already exists exception
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
    public String deleteCompany(RedirectAttributes redirectAttributes) {
        if (!companyService.dbHasCompany()) {
            return "redirect:/admin";
        }

        try {
            companyService.deleteCompany();

            redirectAttributes.addFlashAttribute("successMessage", "Company deleted successfully.");
        } catch (CompanyNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/admin";
    }

}
