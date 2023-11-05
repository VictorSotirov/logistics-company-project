package nbu.bg.logisticscompany;

import nbu.bg.logisticscompany.model.entity.Role;
import nbu.bg.logisticscompany.model.entity.User;
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
    boolean alreadySetup = false;

    public SetupDataLoader(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        User admin = User.builder()
                .roles(new HashSet<>(List.of(new Role("Admin"))))
                .username("admin")
                .password(passwordEncoder.encode("nimda"))
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
        // persist users
        userRepository.save(officeEmp);
        userRepository.save(client);
        userRepository.save(courier);
        userRepository.save(admin);
        alreadySetup = true;
    }
}
