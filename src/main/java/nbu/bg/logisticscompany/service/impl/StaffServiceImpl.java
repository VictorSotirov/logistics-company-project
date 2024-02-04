package nbu.bg.logisticscompany.service.impl;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.model.dto.StaffDto;
import nbu.bg.logisticscompany.model.entity.Order;
import nbu.bg.logisticscompany.model.entity.OrderStatus;
import nbu.bg.logisticscompany.model.entity.User;
import nbu.bg.logisticscompany.model.entity.UserRole;
import nbu.bg.logisticscompany.repository.OrderRepository;
import nbu.bg.logisticscompany.repository.StaffRepository;
import nbu.bg.logisticscompany.repository.UserRepository;
import nbu.bg.logisticscompany.service.OrderService;
import nbu.bg.logisticscompany.service.StaffService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final OrderRepository orderRepository;
    private final StaffRepository staffRepository;
    private final OrderService orderService;
    private final UserRepository userRepository;

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

    @Override
    public List<StaffDto> getAllEmployees() {
        List<StaffDto> result = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            if (userIsEmployee(user)) {
                result.add(StaffDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .role(user.getRoles().stream().map(role -> role.getName().toString()).collect(Collectors.joining()))
                        .build());
            }
        });
        return result;
    }

    private boolean userIsEmployee(User user) {
        return user.getRoles()
                .stream()
                .anyMatch(role -> role.getName().equals(UserRole.OFFICE_EMPLOYEE)
                        || role.getName().equals(UserRole.COURIER));
    }

}
