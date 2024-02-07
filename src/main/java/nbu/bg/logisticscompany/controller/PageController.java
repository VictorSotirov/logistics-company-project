package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.annotation.security.isAdmin;
import nbu.bg.logisticscompany.annotation.security.isClient;
import nbu.bg.logisticscompany.annotation.security.isOfficeEmployee;
import nbu.bg.logisticscompany.annotation.security.isStaff;
import nbu.bg.logisticscompany.model.dto.*;
import nbu.bg.logisticscompany.model.entity.UserRole;
import nbu.bg.logisticscompany.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class PageController {
    private final OrderService orderService;
    private final UserService userService;
    private final StaffService staffService;
    private final CompanyService companyService;

    private final OfficeService officeService;

    @RequestMapping({ "/index", "/", "/home", "*" })
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserRegisterDto userDto,
            HttpServletRequest request) {

        try {
            userService.registerClient(userDto);
        }
        catch (Exception ex) {
            ModelAndView mav = new ModelAndView("register", "user", userDto);
            mav.addObject("errorMessage", ex.getMessage());
            return mav;
        }
        return new ModelAndView("login", "user", userDto);
    }

    @GetMapping("/order")
    @isOfficeEmployee
    public String showOrderPage(Model model) {
        model.addAttribute("order", new OrderDto());
        return "create-order";
    }

    @GetMapping("/order/{id}")
    @isStaff
    public String showUpdateOrder(@PathVariable("id") String id, Model model) throws Exception {
        //TODO validate ID
        OrderDto order = orderService.getOrderByID(Long.parseLong(id));
        model.addAttribute("order", order);
        return "update-order";
    }

    @GetMapping("/orders")
    @isStaff
    public String showOrdersList(Model model) {
        // TODO return orders based on user role and context
        List<OrderDto> orderDtoList = orderService.getAllOrders();
        model.addAttribute("orders", orderDtoList);
        return "orders";
    }


    @GetMapping("/company")
    public String showCompanyData(Model model) {
        Optional<CompanyDto> companyDtoOptional = companyService.getCompanyData();

        companyDtoOptional.ifPresent(companyDto -> model.addAttribute("company", companyDto));

        return "company";
    }

    @GetMapping("/company/edit")
    @isAdmin
    public String showCompanyEditForm(Model model) {
        if (!companyService.dbHasCompany()) {
            return "redirect:/company";
        }

        Optional<CompanyDto> companyDtoOptional = companyService.getCompanyData();

        if (companyDtoOptional.isPresent()) {
            CompanyDto companyDto = new CompanyDto();

            companyDto.setId(companyDtoOptional.get().getId());

            model.addAttribute("company", companyDto);
        }

        return "edit-company";
    }

    @GetMapping("/company/create")
    @isAdmin
    public String showCompanyCreateForm(Model model) {
        if (companyService.dbHasCompany()) {
            return "redirect:/admin";
        }

        model.addAttribute("company", new CompanyDto());

        return "create-company";
    }

    @GetMapping("/company/delete")
    @isAdmin
    public String handleDeleteCompanyGet() {
        return "redirect:/index";
    }

    @GetMapping("/client")
    @isClient
    public String showClientOrders(Model model) {
        return "client-orders";
    }

    @GetMapping("/offices")
    public String showOfficesList(Model model) {

        List<OfficeDto> officeDtoList = officeService.getAllOffices();
        model.addAttribute("offices", officeDtoList);
        return "offices";
    }

    @GetMapping("/office")
    public String showOfficePage(Model model) {
        model.addAttribute("office", new OfficeDto());
        return "create-office";
    }

    @GetMapping("/office/update/{id}")
    public String showUpdateOffice(@PathVariable("id") String id, Model model) throws Exception {

        try {
            Long officeId = Long.parseLong(id);
            OfficeDto office = officeService.getOfficeById(officeId);

            if (office == null) {
                return "offices";
            }
            model.addAttribute("office", office);
        }
        catch (NumberFormatException e) {
            return "offices";
        }
        return "update-office";
    }

    @GetMapping("/office/delete/{id}")
    public String deleteOffice() {
        return "redirect:/offices";
    }

    @GetMapping("/admin/employee/{id}")
    public String updateStaff(@PathVariable("id") String id, Model model) {
        try {
            Long staffId = Long.parseLong(id);
            StaffDto staff = staffService.getStaff(staffId);

            if (staff == null) {
                return "admin";
            }
            model.addAttribute("staff", staff);
        }
        catch (NumberFormatException e) {
            return "admin";
        }
        List<UserRole> roles = Arrays.stream(UserRole.values()).filter(role -> !role.equals(UserRole.CLIENT))
                                     .collect(Collectors.toList());
        model.addAttribute("roles", roles);
        return "update-staff-role";
    }

}
