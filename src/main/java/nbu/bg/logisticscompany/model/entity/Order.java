package nbu.bg.logisticscompany.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_sender")
    private Client sender;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_receiver")
    private Client receiver;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_staff")
    private Staff staff;

    // TODO
    // private Staff courier;  (ManyToOne) with Staff
    // private Staff officeEmployee; (ManyToOne) with Staff
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

    private String deliveryAddress;

    private Boolean isOfficeDelivery;

    private Double weight;

    private Double price;
}
