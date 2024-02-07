package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nbu.bg.logisticscompany.annotation.security.isAdmin;
import nbu.bg.logisticscompany.model.dto.ClientDto;
import nbu.bg.logisticscompany.model.dto.CompanyDto;
import nbu.bg.logisticscompany.model.dto.StaffDto;
import nbu.bg.logisticscompany.service.AdminService;
import nbu.bg.logisticscompany.service.ClientService;
import nbu.bg.logisticscompany.service.CompanyService;
import nbu.bg.logisticscompany.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
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

    @DeleteMapping("/admin/client/delete/{id}")
    @isAdmin
    public String deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return "redirect:/admin";
    }

    @PutMapping("/admin/client/update/{id}")
    @isAdmin
    public String updateClient(@PathVariable("id") Long id, @Valid @ModelAttribute ClientDto updatedClientDto,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/admin";
        }
        clientService.updateClient(id, updatedClientDto);
        return "redirect:/admin";
    }


    @PutMapping("/admin/employee/{id}")
    @isAdmin
    public String updateStaff(@PathVariable("id") String id, @NotNull StaffDto staffDto) throws Exception {
        log.info("Updating employee");
        adminService.updateEmployeeRole(staffDto);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/employees/{id}")
    @isAdmin
    public String deleteStaff(@PathVariable("id") long id) {
        log.info("Deleting employee");
        adminService.deleteEmployee(id);
        return "redirect:/admin";
    }

}
