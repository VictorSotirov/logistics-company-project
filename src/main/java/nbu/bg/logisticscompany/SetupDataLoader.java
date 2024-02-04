package nbu.bg.logisticscompany;

import nbu.bg.logisticscompany.model.entity.Company;
import nbu.bg.logisticscompany.model.entity.Role;
import nbu.bg.logisticscompany.model.entity.User;
import nbu.bg.logisticscompany.repository.CompanyRepository;
import nbu.bg.logisticscompany.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;


@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;
    boolean alreadySetup = false;

    public SetupDataLoader(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           CompanyRepository companyRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        Company company = Company.builder()
                .name("Speedy")
                .address("Ralevica 64")
                .build();

        User admin = User.builder()
                .roles(new HashSet<>(List.of(new Role("Admin"))))
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .build();

        User courier = User.builder()
                .roles(new HashSet<>(List.of(new Role("Courier"))))
                .username("courier")
                .password(passwordEncoder.encode("courier"))
                .build();

        User officeEmp = User.builder()
                .roles(new HashSet<>(List.of(new Role("OfficeEmployee"))))
                .username("office_employee")
                .password(passwordEncoder.encode("office_employee"))
                .build();

        User client = User.builder()
                .roles(new HashSet<>(List.of(new Role("Client"))))
                .username("client")
                .password(passwordEncoder.encode("client"))
                .build();

        //persist company
        companyRepository.save(company);

        // persist users
        userRepository.save(officeEmp);
        userRepository.save(client);
        userRepository.save(courier);
        userRepository.save(admin);

        alreadySetup = true;
    }
}
