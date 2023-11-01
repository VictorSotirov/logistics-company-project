package nbu.bg.logisticscompany.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Staff")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Staff extends User {
    @OneToMany(mappedBy = "staff"
            , cascade = CascadeType.ALL)
    private List<Order> processedOrders;
}
