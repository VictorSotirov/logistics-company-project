package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.model.dto.UserDetailsImpl;
import nbu.bg.logisticscompany.service.CompanyService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@AllArgsConstructor
public class GlobalControllerAdvice {
    private final CompanyService companyService;

    //DYNAMICALLY ADDS THE COMPANY NAME TO THE HEADER, FOOTER, AND INDEX PAGE
    @ModelAttribute
    public void addAttributes(Model model,
                              @AuthenticationPrincipal UserDetailsImpl currentUser) {
        companyService.getCompanyData().ifPresent(companyData -> {
            String companyName = companyData.getName();
            model.addAttribute("companyName", companyName);
        });
        if (currentUser != null) {
            model.addAttribute("currUser", currentUser);
        }
    }

}
