package nbu.bg.logisticscompany.repository;

import nbu.bg.logisticscompany.model.entity.Order;
import nbu.bg.logisticscompany.model.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface Order repository.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Find all by order by id desc list.
     *
     * @return the list
     */
    List<Order> findAllByOrderByIdDesc();

    /**
     * Gets all orders by sender id.
     *
     * @param senderId the sender id
     * @return the all orders by sender id
     */
    @Query("SELECT order FROM Order order WHERE order.sender.id = :senderId")
    List<Order> getAllOrdersBySenderId(@Param("senderId") Long senderId);

    /**
     * Gets all orders by receiver id.
     *
     * @param receiverId the receiver id
     * @return the all orders by receiver id
     */
    @Query("SELECT order FROM Order order WHERE order.receiver.id = :receiverId")
    List<Order> getAllOrdersByReceiverId(@Param("receiverId") Long receiverId);

    /**
     * Find a ll by office employee id list.
     *
     * @param id the id
     * @return the list
     */
    List<Order> findALlByOfficeEmployeeId(Long id);

    /**
     * Find all by status list.
     *
     * @param status the status
     * @return the list
     */
    List<Order> findAllByStatus(OrderStatus status);

    /**
     * Find all by received date before and send date after list.
     *
     * @param receivedDate the received date
     * @param sendDate     the send date
     * @return the list
     */
    List<Order> findAllByReceivedDateBeforeAndSendDateAfter(LocalDate receivedDate, LocalDate sendDate);
}
