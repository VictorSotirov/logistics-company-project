package nbu.bg.logisticscompany.service.impl;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.repository.OrderRepository;
import nbu.bg.logisticscompany.service.ClientService;
import nbu.bg.logisticscompany.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    @Autowired
    private OrderRepository orderRepository;

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
}
