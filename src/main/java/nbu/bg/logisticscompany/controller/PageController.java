package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class PageController {
    private final OrderService orderService;

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
}
