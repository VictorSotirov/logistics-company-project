package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.annotation.security.isOfficeEmployee;
import nbu.bg.logisticscompany.annotation.security.isStaff;
import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.model.dto.UserDetailsImpl;
import nbu.bg.logisticscompany.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    @isOfficeEmployee
    public String createOrder(@Valid OrderDto order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-order";
        }
        UserDetailsImpl officeEmployee = (UserDetailsImpl) model.getAttribute("currUser");
        if (officeEmployee != null) {
            orderService.create(order, officeEmployee.getId());
        }
        return "redirect:/orders";
    }

    @PutMapping("/order/{id}")
    @isStaff
    public String updateOrder(@PathVariable("id") String id, @Valid @ModelAttribute OrderDto order,
                              BindingResult result, Model model) {
        System.out.println("Updating order");
        if (result.hasErrors()) {
            return "update-order";
        }
        UserDetailsImpl staff = (UserDetailsImpl) model.getAttribute("currUser");
        if (staff != null) {
            orderService.updateOrder(order, staff.getId());
        }
        return "redirect:/orders";
    }

    @DeleteMapping("/order/{id}")
    @isStaff
    public String deleteOrder(@PathVariable("id") String id) throws Exception {
        System.out.println("trying to delete");
        orderService.deleteOrder(Long.valueOf(id));
        System.out.println("success deleting");
        return "redirect:/orders";
    }

}
