package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.model.entity.OrderStatus;
import nbu.bg.logisticscompany.service.OrderService;
import nbu.bg.logisticscompany.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/staff")
public class StaffController {
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private StaffService staffService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> receivedOrders = orderService.getAllOrders();
        System.out.println(receivedOrders);
        return new ResponseEntity<>(receivedOrders, HttpStatus.OK);
    }

    @PostMapping("/update/status")
    public ResponseEntity<String> updateOrderStatus(@NonNull Long id, @NotNull OrderStatus orderStatus)
            throws Exception {
        staffService.updateOrderStatus(id, orderStatus);
        return new ResponseEntity<>("Order is updated", HttpStatus.OK);
    }

    @PostMapping("/update/courier")
    public ResponseEntity<String> updateOrderCourier(@NonNull Long orderId, @NonNull Long staffId) throws Exception {
        staffService.updateOrderCourier(orderId, staffId);
        return new ResponseEntity<>("Order is updated", HttpStatus.OK);
    }
}
