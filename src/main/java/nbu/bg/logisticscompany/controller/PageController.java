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

/**
 * The type Page controller.
 */
@Controller
@AllArgsConstructor
public class PageController {
    private final OrderService orderService;
    private final UserService userService;
    private final StaffService staffService;
    private final CompanyService companyService;

    private final OfficeService officeService;
    private final ClientService clientService;

    /**
     * Index string.
     *
     * @return the string
     */
    @RequestMapping({ "/index", "/", "/home", "*" })
    public String index() {
        return "index";
    }

    /**
     * Show login page string.
     *
     * @return the string
     */
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    /**
     * Show register page string.
     *
     * @return the string
     */
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    /**
     * Register user account model and view.
     *
     * @param userDto the user dto
     * @param request the request
     * @return the model and view
     */
    @PostMapping("/register")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserRegisterDto userDto,
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

    /**
     * Show order page string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/order")
    @isOfficeEmployee
    public String showOrderPage(Model model) {
        model.addAttribute("order", new OrderDto());
        return "create-order";
    }

    /**
     * Show update order string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     * @throws Exception the exception
     */
    @GetMapping("/order/{id}")
    @isStaff
    public String showUpdateOrder(@PathVariable("id") String id, Model model) throws Exception {
        //TODO validate ID
        OrderDto order = orderService.getOrderByID(Long.parseLong(id));
        model.addAttribute("order", order);
        return "update-order";
    }

    /**
     * Show orders list string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/orders")
    @isStaff
    public String showOrdersList(Model model) {
        // TODO return orders based on user role and context
        List<OrderDto> orderDtoList = orderService.getAllOrders();
        model.addAttribute("orders", orderDtoList);
        return "orders";
    }


    /**
     * Show company data string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/company")
    public String showCompanyData(Model model) {
        Optional<CompanyDto> companyDtoOptional = companyService.getCompanyData();

        companyDtoOptional.ifPresent(companyDto -> model.addAttribute("company", companyDto));

        return "company";
    }

    /**
     * Show company edit form string.
     *
     * @param model the model
     * @return the string
     */
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

    /**
     * Show company create form string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/company/create")
    @isAdmin
    public String showCompanyCreateForm(Model model) {
        if (companyService.dbHasCompany()) {
            return "redirect:/admin";
        }

        model.addAttribute("company", new CompanyDto());

        return "create-company";
    }

    /**
     * Handle delete company get string.
     *
     * @return the string
     */
    @GetMapping("/company/delete")
    @isAdmin
    public String handleDeleteCompanyGet() {
        return "redirect:/index";
    }

    /**
     * Show client orders string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/client")
    @isClient
    public String showClientOrders(Model model) {
        return "client-orders";
    }

    /**
     * Show offices list string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/offices")
    public String showOfficesList(Model model) {

        List<OfficeDto> officeDtoList = officeService.getAllOffices();
        model.addAttribute("offices", officeDtoList);
        return "offices";
    }

    /**
     * Show office page string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/office")
    public String showOfficePage(Model model) {
        model.addAttribute("office", new OfficeDto());
        return "create-office";
    }

    /**
     * Show update office string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     * @throws Exception the exception
     */
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

    /**
     * Delete office string.
     *
     * @return the string
     */
    @GetMapping("/office/delete/{id}")
    public String deleteOffice() {
        return "redirect:/offices";
    }


    /**
     * Show update client string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     * @throws Exception the exception
     */
    @GetMapping("/admin/client/update/{id}")
    public String showUpdateClient(@PathVariable("id") String id, Model model) throws Exception {

        try {
            Long clientId = Long.parseLong(id);
            ClientDto client = clientService.getClientById(clientId);

            if (client == null) {
                return "admin";
            }
            model.addAttribute("client", client);
            List<UserRole> roles = Arrays.stream(UserRole.values()).filter(role -> !role.equals(UserRole.CLIENT))
                                         .filter(userRole -> !userRole.equals(UserRole.ADMIN))
                                         .collect(Collectors.toList());
            model.addAttribute("roles", roles);
        } catch (NumberFormatException e) {
            return "admin";
        }
        return "update-client";
    }

    /**
     * Delete client string.
     *
     * @return the string
     */
    @GetMapping("/admin/client/delete/{id}")
    public String deleteClient() {
        return "redirect:/admin";
    }

    /**
     * Update staff string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/admin/employee/{id}")
    public String showUpdateStaff(@PathVariable("id") String id, Model model) {
        try {
            Long staffId = Long.parseLong(id);
            //finds the existing staff as a dto
            StaffDto staff = staffService.getStaff(staffId);

            //if it doesn't exist do nothing
            if (staff == null) {
                return "admin";
            }
            model.addAttribute("staff", staff);
        }
        catch (NumberFormatException e) {
            return "admin";
        }
        //filters the viable roles for updating the employee, and sets it in the model
        List<UserRole> roles = Arrays.stream(UserRole.values()).filter(role -> !role.equals(UserRole.CLIENT))
                                     .collect(Collectors.toList());
        model.addAttribute("roles", roles);
        return "update-staff-role";
    }

}
