package nbu.bg.logisticscompany.repository;

import nbu.bg.logisticscompany.model.entity.Order;
import nbu.bg.logisticscompany.model.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderByIdDesc();

    @Query("SELECT order FROM Order order WHERE order.sender.id = :senderId")
    List<Order> getAllOrdersBySenderId(@Param("senderId") Long senderId);

    @Query("SELECT order FROM Order order WHERE order.receiver.id = :receiverId")
    List<Order> getAllOrdersByReceiverId(@Param("receiverId") Long receiverId);

    List<Order> findALlByOfficeEmployeeId(Long id);

    List<Order> findAllByStatus(OrderStatus status);
}
