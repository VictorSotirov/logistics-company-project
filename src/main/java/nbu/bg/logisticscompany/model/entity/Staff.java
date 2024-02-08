package nbu.bg.logisticscompany.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * The type Staff.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class Staff extends User {
    @OneToMany(mappedBy = "officeEmployee"
            , cascade = CascadeType.ALL)
    private List<Order> processedOrders;

    @OneToMany(mappedBy = "courier"
            , cascade = CascadeType.ALL)
    private List<Order> deliveredOrders;
}
