package nbu.bg.logisticscompany.service.impl;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.model.dto.ClientDto;
import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.model.entity.User;
import nbu.bg.logisticscompany.model.entity.UserRole;
import nbu.bg.logisticscompany.repository.OrderRepository;
import nbu.bg.logisticscompany.repository.UserRepository;
import nbu.bg.logisticscompany.service.ClientService;
import nbu.bg.logisticscompany.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public List<OrderDto> getReceivedOrders(Long id) {
        return orderRepository.getAllOrdersByReceiverId(id).stream().map(OrderService::mapOrderToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getSentOrders(Long id) {
        return orderRepository.getAllOrdersBySenderId(id).stream().map(OrderService::mapOrderToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDto> getAllClients() {
        List<ClientDto> result = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            if (userIsClient(user)) {
                result.add(ClientDto.builder()
                        .username(user.getUsername())
                        .id(user.getId())
                        .build());
            }
        });
        return result;
    }

    private boolean userIsClient(User user) {
        return user.getRoles()
                .stream()
                .anyMatch(role -> role.getName().equals(UserRole.CLIENT));
    }

}
