package nbu.bg.logisticscompany.service.impl;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.model.entity.Order;
import nbu.bg.logisticscompany.model.entity.OrderStatus;
import nbu.bg.logisticscompany.repository.OrderRepository;
import nbu.bg.logisticscompany.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAllByOrderByIdDesc();
        List<OrderDto> mappedOrders = new ArrayList<>(orders.size());
        for (Order o : orders) {
            mappedOrders.add(mapOrderToOrderDTO(o));
        }
        return mappedOrders;
    }

    private OrderDto mapOrderToOrderDTO(Order o) {
        return OrderDto.builder()
                .id(o.getId())
                .isOfficeDelivery(o.getIsOfficeDelivery())
                .deliveryAddress(o.getDeliveryAddress())
                .weight(o.getWeight())
                .totalPrice(o.getPrice())
                .orderStatus(o.getStatus())
                .build();
    }

    @Override
    public void create(OrderDto input) {
        // TODO - Find sender by given input
        // TODO - Find receiver by given input
        // TODO - Find staff by given input
        Order order = Order.builder()
                .isOfficeDelivery(input.getIsOfficeDelivery())
                .deliveryAddress(input.getDeliveryAddress())
                .weight(input.getWeight())
                .price(input.getTotalPrice())
                .status(OrderStatus.SENT)
                .build();
        orderRepository.save(order);
    }

    @Override
    public OrderDto getOrderByID(long id) throws Exception {
        Optional<Order> orderById = orderRepository.findById(id);
        if (orderById.isEmpty()) {
            throw new Exception();
        }
        return mapOrderToOrderDTO(orderById.get());
    }

    @Override
    public void deleteOrder(OrderDto order) {

    }


}
