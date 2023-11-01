package nbu.bg.logisticscompany.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    private Long id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "company",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Office> offices;
}
