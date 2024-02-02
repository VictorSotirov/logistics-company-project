package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();

    void create(OrderDto order);

    OrderDto getOrderByID(long id) throws Exception;

    void deleteOrder(Long id) throws Exception;

    void updateOrder(OrderDto order);
}
