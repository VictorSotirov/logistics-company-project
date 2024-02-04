package nbu.bg.logisticscompany.model.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String address;

    @OneToMany(mappedBy = "company",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Office> offices;
}
