package nbu.bg.logisticscompany;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.model.entity.*;
import nbu.bg.logisticscompany.repository.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;


@Component
@AllArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;
    private final OfficeRepository officeRepository;
    private final StaffRepository staffRepository;
    private final ClientRepository clientRepository;
    static boolean alreadySetup = false;


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

        Staff courier = Staff.builder()
                .roles(new HashSet<>(List.of(new Role("Courier"))))
                .username("courier")
                .password(passwordEncoder.encode("courier"))
                .build();

        Staff officeEmp = Staff.builder()
                .roles(new HashSet<>(List.of(new Role("OfficeEmployee"))))
                .username("office_employee")
                .password(passwordEncoder.encode("office_employee"))
                .build();

        Client client = Client.builder()
                .roles(new HashSet<>(List.of(new Role("Client"))))
                .username("client")
                .password(passwordEncoder.encode("client"))
                .build();

        Client client2 = Client.builder()
                .roles(new HashSet<>(List.of(new Role("Client"))))
                .username("sender")
                .password(passwordEncoder.encode("sender"))
                .build();

        Client client3 = Client.builder()
                .roles(new HashSet<>(List.of(new Role("Client"))))
                .username("receiver")
                .password(passwordEncoder.encode("receiver"))
                .build();

        //persist company
        companyRepository.save(company);

        // persist users
        staffRepository.save(officeEmp);
        clientRepository.save(client);
        clientRepository.save(client2);
        clientRepository.save(client3);

        staffRepository.save(courier);
        userRepository.save(admin);

        alreadySetup = true;
    }
}
