package nbu.bg.logisticscompany.model.entity;


import lombok.*;

import javax.persistence.*;

/**
 * The type Office.
 */
@Entity
@Table(name = "Office")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "ID")
    private Long id;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_company")
    private Company company;
}
