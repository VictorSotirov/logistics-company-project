package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

/**
 * The type Client controller.
 */
@Controller
@AllArgsConstructor
@Slf4j
public class ClientController {
    private final UserRepository userRepository;
    private ClientService clientService;

    /**
     * Gets received orders.
     *
     * @param authentication the authentication
     * @param model          the model
     * @return the received orders
     */
    @GetMapping("/client/received")
    @isClient
    public String getReceivedOrders(Authentication authentication, Model model) {
        //checks the authentication
        if (authentication == null) {
            throw new RuntimeException();
        }
        //finds client by username
        Optional<User> foundUser = userRepository.findByUsername(authentication.getName());
        //gets client's received orders
        List<OrderDto> receivedOrders = clientService.getReceivedOrders(
                foundUser.orElseThrow(RuntimeException::new).getId());
        log.info(receivedOrders.toString());
        model.addAttribute("receivedOrders", receivedOrders);
        return "received-orders";
    }

    /**
     * Gets sent orders.
     *
     * @param authentication the authentication
     * @param model          the model
     * @return the sent orders
     */
    @GetMapping("/client/sent")
    @isClient
    public String getSentOrders(Authentication authentication, Model model) {
        //checks the authentication
        if (authentication == null) {
            throw new RuntimeException();
        }
        //finds client by username
        Optional<User> foundUser = userRepository.findByUsername(authentication.getName());
        //gets client's sent orders
        List<OrderDto> sentOrders = clientService.getSentOrders(foundUser.orElseThrow(RuntimeException::new).getId());
        log.info(sentOrders.toString());
        model.addAttribute("sentOrders", sentOrders);
        return "sent-orders";
    }
}
