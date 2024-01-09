package nbu.bg.logisticscompany.controller;

import nbu.bg.logisticscompany.model.dto.OrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/order")
    public String showOrderPage(Model model) {
        model.addAttribute("order", new OrderDto());
        return "order";
    }
}
