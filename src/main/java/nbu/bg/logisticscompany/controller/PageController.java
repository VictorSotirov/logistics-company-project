package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.annotation.security.isAdmin;
import nbu.bg.logisticscompany.annotation.security.isClient;
import nbu.bg.logisticscompany.annotation.security.isOfficeEmployee;
import nbu.bg.logisticscompany.annotation.security.isStaff;
import nbu.bg.logisticscompany.model.dto.*;
import nbu.bg.logisticscompany.repository.ClientRepository;
import nbu.bg.logisticscompany.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class PageController {
    private final OrderService orderService;
    private final UserService userService;
    private final CompanyService companyService;

    private final OfficeService officeService;
    private final ClientService clientService;

    @RequestMapping({"/index", "/", "/home"})
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
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserRegisterDto userDto,
            HttpServletRequest request) {

        try {
            userService.registerClient(userDto);
        } catch (Exception ex) {
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
        } catch (NumberFormatException e) {
            return "offices";
        }
        return "update-office";
    }

    @GetMapping("/office/delete/{id}")
    public String deleteOffice() {
        return "redirect:/offices";
    }


    @GetMapping("/admin/client/update/{id}")
    public String showUpdateClient(@PathVariable("id") String id, Model model) throws Exception {

        try {
            Long clientId = Long.parseLong(id);
            ClientDto client = clientService.getClientById(clientId);

            if (client == null) {
                return "admin";
            }
            model.addAttribute("client", client);
        } catch (NumberFormatException e) {
            return "admin";
        }
        return "update-client";
    }

    @GetMapping("/admin/client/delete/{id}")
    public String deleteClient() {
        return "redirect:/admin";
    }

}
