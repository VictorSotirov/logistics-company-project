package nbu.bg.logisticscompany.controller;

import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class OrderController {
    private OrderService orderService;

    @PostMapping("/order")
    public String createOrder(@Valid OrderDto order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "order";
        }
        orderService.create(order);
        return "redirect:/orders";
    }

    @GetMapping("/order/{id}")
    public String showUpdateOrder(@PathVariable("id") long id, Model model) throws Exception {
        OrderDto order = orderService.getOrderByID(id);
        model.addAttribute("order", order);
        return "update-order";
    }

    @PostMapping("/order/{id}")
    public String updateOrder(@PathVariable("id") long id, @Valid OrderDto order,
                              BindingResult result, Model model) {
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) throws Exception {
        OrderDto order = orderService.getOrderByID(id);
        orderService.deleteOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String showOrdersList(Model model) {
        // TODO return orders based on user role
        model.addAttribute("ordersList", orderService.getAllOrders());
        return "index";
    }

}
