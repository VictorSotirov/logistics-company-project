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

@Controller
@AllArgsConstructor
public class OfficeController {

    private final OfficeService officeService;

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

    @DeleteMapping("/office/delete/{id}")
    @isAdmin
    public String deleteOffice(@PathVariable("id") Long id) {
        officeService.deleteOffice(id);
        return "redirect:/offices";
    }

}
