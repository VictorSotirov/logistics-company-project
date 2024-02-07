package nbu.bg.logisticscompany.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.DETACH})
    @JoinColumn(name = "fk_sender")
    private Client sender;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.DETACH})
    @JoinColumn(name = "fk_receiver")
    private Client receiver;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.DETACH})
    @JoinColumn(name = "fk_staff")
    private Staff officeEmployee;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.DETACH})
    @JoinColumn(name = "fk_courier")
    private Staff courier;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

    private String deliveryAddress;

    private Boolean isOfficeDelivery;

    private Double weight;
    private Double price;
    private LocalDate sendDate;
    private LocalDate receivedDate;
}
