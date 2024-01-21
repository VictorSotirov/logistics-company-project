package nbu.bg.logisticscompany.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Office")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "ID")
    private Long id;

    @Column(name = "address", unique = true, nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_company")
    private Company company;
}
