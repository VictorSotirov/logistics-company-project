package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.annotation.security.isAdmin;
import nbu.bg.logisticscompany.model.dto.CompanyDto;
import nbu.bg.logisticscompany.service.ClientService;
import nbu.bg.logisticscompany.service.CompanyService;
import nbu.bg.logisticscompany.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class AdminController {
    private final ClientService clientService;
    private final StaffService staffService;
    private final CompanyService companyService;

    @RequestMapping("/admin")
    @isAdmin
    public String adminPage(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("employees", staffService.getAllEmployees());

        Optional<CompanyDto> companyDtoOptional = companyService.getCompanyData();

        companyDtoOptional.ifPresent(companyDto -> model.addAttribute("company", companyDto));
        return "admin";
    }
}
