package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.annotation.security.isAdmin;
import nbu.bg.logisticscompany.service.ClientService;
import nbu.bg.logisticscompany.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class AdminController {
    private final ClientService clientService;
    private final StaffService staffService;

    @RequestMapping("/admin")
    @isAdmin
    public String adminPage(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("employees", staffService.getAllEmployees());
        return "admin";
    }
}
