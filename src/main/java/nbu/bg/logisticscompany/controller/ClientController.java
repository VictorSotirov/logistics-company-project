package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.annotation.security.isClient;
import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.model.entity.User;
import nbu.bg.logisticscompany.repository.UserRepository;
import nbu.bg.logisticscompany.service.ClientService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ClientController {
    private final UserRepository userRepository;
    private ClientService clientService;

    @GetMapping("/client/received")
    @isClient
    public String getReceivedOrders(Authentication authentication, Model model) {
        if (authentication == null) {
            throw new RuntimeException();
        }
        Optional<User> foundUser = userRepository.findByUsername(authentication.getName());
        List<OrderDto> receivedOrders = clientService.getReceivedOrders(
                foundUser.orElseThrow(RuntimeException::new).getId());
        System.out.println(receivedOrders);
        model.addAttribute("receivedOrders", receivedOrders);
        return "received-orders";
    }

    @GetMapping("/client/sent")
    @isClient
    public String getSentOrders(Authentication authentication, Model model) {
        if (authentication == null) {
            throw new RuntimeException();
        }
        Optional<User> foundUser = userRepository.findByUsername(authentication.getName());
        List<OrderDto> sentOrders = clientService.getSentOrders(foundUser.orElseThrow(RuntimeException::new).getId());
        System.out.println(sentOrders);
        model.addAttribute("sentOrders", sentOrders);
        return "sent-orders";
    }
}
