package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.StaffDto;
import nbu.bg.logisticscompany.model.entity.OrderStatus;

import java.util.List;

/**
 * The interface Staff service.
 */
public interface StaffService {
    /**
     * Gets staff.
     *
     * @param id the id
     * @return the staff
     */
    StaffDto getStaff(Long id);

    /**
     * Update order status.
     *
     * @param id          the id
     * @param orderStatus the order status
     * @throws Exception the exception
     */
    void updateOrderStatus(Long id, OrderStatus orderStatus) throws Exception;

    /**
     * Update order courier.
     *
     * @param orderId the order id
     * @param staffId the staff id
     * @throws Exception the exception
     */
    void updateOrderCourier(Long orderId, Long staffId) throws Exception;

    /**
     * Gets all employees.
     *
     * @return the all employees
     */
    List<StaffDto> getAllEmployees();
}
