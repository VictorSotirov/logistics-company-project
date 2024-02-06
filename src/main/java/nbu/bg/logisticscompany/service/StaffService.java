package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.StaffDto;
import nbu.bg.logisticscompany.model.entity.OrderStatus;

import java.util.List;

public interface StaffService {
    void updateOrderStatus(Long id, OrderStatus orderStatus) throws Exception;

    void updateOrderCourier(Long orderId, Long staffId) throws Exception;

    List<StaffDto> getAllEmployees();
}
