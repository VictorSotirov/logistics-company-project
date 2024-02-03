package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.service.CompanyService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

//MIGHT NEED TO MOVE TO GLOBAL EXCEPTION HANDLER
@ControllerAdvice
@AllArgsConstructor
public class GlobalControllerAdvice
{
    private final CompanyService companyService;

    @ModelAttribute
    public void addAttributes(Model model)
    {
        companyService.getCompanyData().ifPresent(companyData -> {
            String companyName = companyData.getName();
            model.addAttribute("companyName", companyName);
        });
    }

}
