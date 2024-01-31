package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/recieved{id}")
    public ResponseEntity<List<OrderDto>> getRecievedOrders(@NonNull Long id) {
        List<OrderDto> receivedOrders = clientService.getReceivedOrders(id);
        System.out.println(receivedOrders);
        return new ResponseEntity<>(receivedOrders, HttpStatus.OK);
    }

    @GetMapping("/sent{id}")
    public ResponseEntity<List<OrderDto>> getSentOrders(@NonNull Long id) {
        List<OrderDto> sentOrders = clientService.getSentOrders(id);
        System.out.println(sentOrders);
        return new ResponseEntity<>(sentOrders, HttpStatus.OK);
    }
}
