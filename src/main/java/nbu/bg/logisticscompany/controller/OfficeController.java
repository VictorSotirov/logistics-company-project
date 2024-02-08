package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.annotation.security.isAdmin;
import nbu.bg.logisticscompany.model.dto.OfficeDto;
import nbu.bg.logisticscompany.service.OfficeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The type Office controller.
 */
@Controller
@AllArgsConstructor
public class OfficeController {

    private final OfficeService officeService;

    /**
     * Create office string.
     *
     * @param officeDto the office dto
     * @param result    the result
     * @param model     the model
     * @return the string
     */
    @PostMapping("/office")
    @isAdmin
    public String createOffice(@Valid OfficeDto officeDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-office";
        }

        try {
            officeService.addOffice(officeDto);
        } catch (Exception e){
            return "redirect:/404";
        }
        return "redirect:/offices";
    }

    /**
     * Update office string.
     *
     * @param id               the id
     * @param updatedOfficeDto the updated office dto
     * @param result           the result
     * @param model            the model
     * @return the string
     */
    @PutMapping("/office/update/{id}")
    @isAdmin
    public String updateOffice(@PathVariable("id") Long id,
                               @Valid @ModelAttribute OfficeDto updatedOfficeDto,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-office";
        }
        try {
            officeService.updateOffice(id, updatedOfficeDto);
        } catch (Exception e){
            return "redirect:/404";
        }

        return "redirect:/offices";
    }

    /**
     * Delete office string.
     *
     * @param id the id
     * @return the string
     */
    @DeleteMapping("/office/delete/{id}")
    @isAdmin
    public String deleteOffice(@PathVariable("id") Long id) {
        officeService.deleteOffice(id);
        return "redirect:/offices";
    }

}
