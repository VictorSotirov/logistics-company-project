package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.annotation.security.isAdmin;
import nbu.bg.logisticscompany.model.dto.CompanyDto;
import nbu.bg.logisticscompany.model.dto.StaffDto;
import nbu.bg.logisticscompany.service.AdminService;
import nbu.bg.logisticscompany.service.ClientService;
import nbu.bg.logisticscompany.service.CompanyService;
import nbu.bg.logisticscompany.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AdminController {
    private final ClientService clientService;
    private final StaffService staffService;
    private final CompanyService companyService;
    private final AdminService adminService;

    @RequestMapping("/admin")
    @isAdmin
    public String adminPage(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("employees", staffService.getAllEmployees());

        Optional<CompanyDto> companyDtoOptional = companyService.getCompanyData();

        companyDtoOptional.ifPresent(companyDto -> model.addAttribute("company", companyDto));
        return "admin";
    }

    @PutMapping("/admin/employee/{id}")
    @isAdmin
    public String updateStaff(@PathVariable("id") String id, @NotNull StaffDto staffDto) throws Exception {
        System.out.println("Updating employee");
        adminService.updateEmployeeRole(staffDto);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/employees/{id}")
    @isAdmin
    public String deleteStaff(@PathVariable("id") long id) {
        System.out.println("Deleting employee");
        adminService.deleteEmployee(id);
        return "redirect:/admin";
    }
}
