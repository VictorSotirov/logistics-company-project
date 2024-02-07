package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.model.entity.Order;

import java.util.List;

public interface OrderService {
    static OrderDto mapOrderToOrderDTO(Order o) {
        return OrderDto.builder().id(o.getId()).orderStatus(o.getStatus()).isOfficeDelivery(o.getIsOfficeDelivery())
                .deliveryAddress(o.getDeliveryAddress()).weight(o.getWeight())
                .senderUsername(o.getSender().getUsername())
                .receiverUsername(o.getReceiver().getUsername())
                .totalPrice(o.getPrice()).build();
    }

    static Order mapOrderDtoToOrder(OrderDto input) {
        return Order.builder().id(input.getId()).isOfficeDelivery(input.getIsOfficeDelivery())
                .deliveryAddress(input.getDeliveryAddress()).weight(input.getWeight()).price(input.getTotalPrice())
                .status(input.getOrderStatus()).build();
    }

    List<OrderDto> getAllOrders();

    void create(OrderDto order, Long id);

    OrderDto getOrderByID(long id) throws Exception;

    void deleteOrder(Long id) throws Exception;

    void updateOrder(OrderDto order, Long id);
}
