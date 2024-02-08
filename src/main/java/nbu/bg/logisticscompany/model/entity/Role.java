package nbu.bg.logisticscompany.model.entity;

import lombok.*;

import javax.persistence.*;

/**
 * The type Role.
 */
@Entity
@Table(name = "Role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRole name;

    /**
     * Instantiates a new Role.
     *
     * @param name the name
     */
    public Role(String name) {
        switch (name) {
            case "Admin":
                this.name = UserRole.ADMIN;
                break;
            case "Client":
                this.name = UserRole.CLIENT;
                break;
            case "OfficeEmployee":
                this.name = UserRole.OFFICE_EMPLOYEE;
                break;
            case "Courier":
                this.name = UserRole.COURIER;
                break;
            default:
                throw new IllegalArgumentException("Invalid user role");
        }
    }
}
