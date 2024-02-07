package nbu.bg.logisticscompany.service.impl;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.model.dto.UserDetailsImpl;
import nbu.bg.logisticscompany.model.entity.Client;
import nbu.bg.logisticscompany.model.entity.Order;
import nbu.bg.logisticscompany.model.entity.OrderStatus;
import nbu.bg.logisticscompany.model.entity.Staff;
import nbu.bg.logisticscompany.repository.ClientRepository;
import nbu.bg.logisticscompany.repository.OrderRepository;
import nbu.bg.logisticscompany.repository.StaffRepository;
import nbu.bg.logisticscompany.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final StaffRepository staffRepository;

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAllByOrderByIdDesc();
        List<OrderDto> mappedOrders = new ArrayList<>(orders.size());
        for (Order o : orders) {
            mappedOrders.add(OrderService.mapOrderToOrderDTO(o));
        }
        return mappedOrders;
    }

    @Override
    public void create(OrderDto input, Long id) {
        Optional<Client> receiver = clientRepository.findByUsername(input.getReceiverUsername());
        if (receiver.isEmpty()) {
            throw new RuntimeException("Receiver not found");
        }

        Optional<Client> sender = clientRepository.findByUsername(input.getSenderUsername());
        if (sender.isEmpty()) {
            throw new RuntimeException("Sender not found");
        }

        Optional<Staff> officeEmployee = staffRepository.findById(id);
        if (officeEmployee.isEmpty()) {
            throw new RuntimeException("Staff not found");
        }

        input.setOrderStatus(OrderStatus.SENT);
        Order order = OrderService.mapOrderDtoToOrder(input);
        order.setReceiver(receiver.get());
        order.setSender(sender.get());
        order.setOfficeEmployee(officeEmployee.get());

        orderRepository.save(order);
    }

    @Override
    public OrderDto getOrderByID(long id) throws Exception {
        Optional<Order> orderById = orderRepository.findById(id);
        if (orderById.isEmpty()) {
            throw new Exception();
        }
        return OrderService.mapOrderToOrderDTO(orderById.get());
    }

    @Override
    public void deleteOrder(Long id) throws Exception {
        System.out.println(id);
        orderRepository.deleteById(id);
    }

    @Override
    public void updateOrder(OrderDto orderDto, UserDetailsImpl staff) {
        Optional<Client> receiver = clientRepository.findByUsername(orderDto.getReceiverUsername());
        if (receiver.isEmpty()) {
            throw new RuntimeException("Receiver not found");
        }

        Optional<Client> sender = clientRepository.findByUsername(orderDto.getSenderUsername());
        if (sender.isEmpty()) {
            throw new RuntimeException("Sender not found");
        }

        Order order = OrderService.mapOrderDtoToOrder(orderDto);
        order.setReceiver(receiver.get());
        order.setSender(sender.get());
        if (orderDto.getIsOfficeDelivery()) {
            // no courier
            Optional<Staff> officeEmployee = staffRepository.findById(staff.getId());
            if (officeEmployee.isEmpty()) {
                throw new RuntimeException("Staff not found");
            }
            order.setOfficeEmployee(officeEmployee.get());
        } else {
            if (staff.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase("Courier")
            )) {
                Optional<Staff> courier = staffRepository.findById(staff.getId());
                if (courier.isEmpty()) {
                    throw new RuntimeException("Staff not found");
                }
                order.setCourier(courier.get());
            }
        }

        orderRepository.save(order);
    }
}
