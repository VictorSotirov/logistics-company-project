package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.model.dto.UserDetailsImpl;
import nbu.bg.logisticscompany.model.entity.Order;

import java.util.List;

/**
 * The interface Order service.
 */
public interface OrderService {
    /**
     * Map order to order dto order dto.
     *
     * @param o the o
     * @return the order dto
     */
    static OrderDto mapOrderToOrderDTO(Order o) {
        return OrderDto.builder().id(o.getId()).orderStatus(o.getStatus()).isOfficeDelivery(o.getIsOfficeDelivery())
                .deliveryAddress(o.getDeliveryAddress()).weight(o.getWeight())
                .senderUsername(o.getSender().getUsername())
                .receiverUsername(o.getReceiver().getUsername())
                .totalPrice(o.getPrice()).build();
    }

    /**
     * Map order dto to order order.
     *
     * @param input the input
     * @return the order
     */
    static Order mapOrderDtoToOrder(OrderDto input) {
        return Order.builder().id(input.getId()).isOfficeDelivery(input.getIsOfficeDelivery())
                .deliveryAddress(input.getDeliveryAddress()).weight(input.getWeight()).price(input.getTotalPrice())
                .status(input.getOrderStatus()).build();
    }

    /**
     * Gets all orders.
     *
     * @return the all orders
     */
    List<OrderDto> getAllOrders();

    /**
     * Create.
     *
     * @param order the order
     * @param id    the id
     */
    void create(OrderDto order, Long id);

    /**
     * Gets order by id.
     *
     * @param id the id
     * @return the order by id
     * @throws Exception the exception
     */
    OrderDto getOrderByID(long id) throws Exception;

    /**
     * Delete order.
     *
     * @param id the id
     * @throws Exception the exception
     */
    void deleteOrder(Long id) throws Exception;

    /**
     * Update order.
     *
     * @param order the order
     * @param id    the id
     */
    void updateOrder(OrderDto order, UserDetailsImpl id);
}
