package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.model.dto.CompanyDto;
import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.model.dto.UserRegisterDto;
import nbu.bg.logisticscompany.service.CompanyService;
import nbu.bg.logisticscompany.service.OrderService;
import nbu.bg.logisticscompany.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping({"/index", "/", "/home"})
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
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
    public String showOrderPage(Model model) {
        model.addAttribute("order", new OrderDto());
        return "create-order";
    }

    @GetMapping("/order/{id}")
    public String showUpdateOrder(@PathVariable("id") String id, Model model) throws Exception {
        //TODO validate ID
        OrderDto order = orderService.getOrderByID(Long.parseLong(id));
        model.addAttribute("order", order);
        return "update-order";
    }

    @GetMapping("/orders")
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
    public String showCompanyCreateForm(Model model) {
        if (companyService.dbHasCompany()) {
            return "redirect:/company";
        }

        model.addAttribute("company", new CompanyDto());

        return "create-company";
    }

    //MIGHT NEED TO CHANGE REDIRECTING WHEN PAGE IS FIXED
    @GetMapping("/company/delete")
    public String handleDeleteCompanyGet() {
        return "redirect:/index";
    }

    @GetMapping("/client")
    public String showClientOrders(Model model) {
        return "client-orders";
    }
}
