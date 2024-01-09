package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();

    void create(OrderDto order);

    OrderDto getOrderByID(long id) throws Exception;

    void deleteOrder(OrderDto order);
}
