package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public String createOrder(@Valid OrderDto order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-order";
        }
        System.out.println(order);
        orderService.create(order);
        return "redirect:/orders";
    }

    @PostMapping("/order/{id}")
    public String updateOrder(@PathVariable("id") long id, @Valid OrderDto order,
                              BindingResult result, Model model) {
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") long id, Model model) throws Exception {
        OrderDto order = orderService.getOrderByID(id);
        orderService.deleteOrder(order);
        return "redirect:/orders";
    }

}
