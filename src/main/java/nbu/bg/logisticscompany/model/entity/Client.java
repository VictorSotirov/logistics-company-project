package nbu.bg.logisticscompany.model.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Client")
public class Client extends User {
    @OneToMany(mappedBy = "receiver",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Order> receivedOrders;

    @OneToMany(mappedBy = "sender",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Order> sendOrders;

}
