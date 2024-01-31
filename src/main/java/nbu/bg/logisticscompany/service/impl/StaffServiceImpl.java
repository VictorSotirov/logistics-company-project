package nbu.bg.logisticscompany.service.impl;

import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.model.entity.Order;
import nbu.bg.logisticscompany.model.entity.OrderStatus;
import nbu.bg.logisticscompany.repository.OrderRepository;
import nbu.bg.logisticscompany.repository.StaffRepository;
import nbu.bg.logisticscompany.service.OrderService;
import nbu.bg.logisticscompany.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private OrderService orderService;

    @Override
    public void updateOrderStatus(Long id, OrderStatus orderStatus) throws Exception {
        OrderDto foundOrder = orderService.getOrderByID(id);
        foundOrder.setOrderStatus(orderStatus);
        orderRepository.save(OrderService.mapOrderDtoToOrder(foundOrder));
    }

    @Override
    public void updateOrderCourier(Long orderId, Long staffId) throws Exception {
        OrderDto foundOrder = orderService.getOrderByID(orderId);
        Order updatedOrder = staffRepository.findById(staffId).map(staff -> {
            Order orderEntity = OrderService.mapOrderDtoToOrder(foundOrder);
            orderEntity.setStaff(staff);
            return orderEntity;
        }).orElseThrow(RuntimeException::new);
        orderRepository.save(updatedOrder);
    }
}
