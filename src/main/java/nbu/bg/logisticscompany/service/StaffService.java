package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.entity.OrderStatus;

public interface StaffService {
    void updateOrderStatus(Long id, OrderStatus orderStatus) throws Exception;

    void updateOrderCourier(Long orderId, Long staffId) throws Exception;
}
