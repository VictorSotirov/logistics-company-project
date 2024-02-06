package nbu.bg.logisticscompany.service.impl;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.exceptions.InvalidRegistration;
import nbu.bg.logisticscompany.model.dto.UserRegisterDto;
import nbu.bg.logisticscompany.model.entity.Client;
import nbu.bg.logisticscompany.model.entity.Role;
import nbu.bg.logisticscompany.model.entity.UserRole;
import nbu.bg.logisticscompany.repository.ClientRepository;
import nbu.bg.logisticscompany.repository.StaffRepository;
import nbu.bg.logisticscompany.repository.UserRepository;
import nbu.bg.logisticscompany.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final StaffRepository staffRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;


    @Override
    public boolean registerClient(UserRegisterDto userRegisterDto) {
        if (userRepository.existsByUsername(userRegisterDto.getUsername())) {
            throw new InvalidRegistration("Username is already taken!");
        }
        Client newClient = Client.builder()
                .username(userRegisterDto.getUsername())
                .password(encoder.encode(userRegisterDto.getPassword()))
                .roles(defaultRoles())
                .receivedOrders(new ArrayList<>())
                .sendOrders(new ArrayList<>())
                .build();
        clientRepository.save(newClient);
        return true;
    }

    private Set<Role> defaultRoles() {
        Set<Role> defaultRoles = new HashSet<>();
        defaultRoles.add(Role.builder().name(UserRole.CLIENT).build());
        return defaultRoles;
    }
}
