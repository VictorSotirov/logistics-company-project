package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
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
    public String createOffice(@Valid OfficeDto officeDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-office";
        }
        officeService.addOffice(officeDto);
        return "redirect:/offices";
    }

    @PutMapping("/office/update/{id}")
    public String updateOffice(@PathVariable("id") Long id,
                               @Valid @ModelAttribute OfficeDto updatedOfficeDto,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-office";
        }
        officeService.updateOffice(id, updatedOfficeDto);
        return "redirect:/offices";
    }

    @DeleteMapping("/office/delete/{id}")
    public String deleteOffice(@PathVariable("id") Long id) {
        officeService.deleteOffice(id);
        return "redirect:/offices";
    }
}
