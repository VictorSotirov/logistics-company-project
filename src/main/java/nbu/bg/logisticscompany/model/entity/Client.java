package nbu.bg.logisticscompany.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
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
