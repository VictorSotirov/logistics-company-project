package nbu.bg.logisticscompany.service.impl;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.model.dto.ClientDto;
import nbu.bg.logisticscompany.model.dto.OrderDto;
import nbu.bg.logisticscompany.model.entity.*;
import nbu.bg.logisticscompany.repository.ClientRepository;
import nbu.bg.logisticscompany.repository.OrderRepository;
import nbu.bg.logisticscompany.repository.StaffRepository;
import nbu.bg.logisticscompany.repository.UserRepository;
import nbu.bg.logisticscompany.service.ClientService;
import nbu.bg.logisticscompany.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final StaffRepository staffRepository;

    @Override
    public List<OrderDto> getReceivedOrders(Long id) {
        //gets received orders, where client id is receiver id
        return orderRepository.getAllOrdersByReceiverId(id).stream().map(OrderService::mapOrderToOrderDTO)
                              .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getSentOrders(Long id) {
        //gets sent orders, where client id is sender id
        return orderRepository.getAllOrdersBySenderId(id).stream().map(OrderService::mapOrderToOrderDTO)
                              .collect(Collectors.toList());
    }

    @Override
    public List<ClientDto> getAllClients() {
        List<ClientDto> result = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            if (userIsClient(user)) {
                result.add(ClientDto.builder().username(user.getUsername()).id(user.getId()).build());
            }
        });
        return result;
    }

    @Override
    public void updateClient(Long id, ClientDto updatedClientDto) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        clientOptional.ifPresent(client -> {
            client.setUsername(updatedClientDto.getUsername());
            if (updatedClientDto.getRole() != null && !updatedClientDto.getRole().isBlank()) {
                switch (updatedClientDto.getRole().toUpperCase()) {
                    case "COURIER":
                        Staff courier = Staff.builder().roles(new HashSet<>(List.of(new Role("Courier"))))
                                             .username(client.getUsername()).password(client.getPassword()).build();
                        staffRepository.save(courier);
                        clientRepository.deleteById(client.getId());
                        return;
                    case "OFFICE_EMPLOYEE":
                        Staff officeEmp = Staff.builder().roles(new HashSet<>(List.of(new Role("OfficeEmployee"))))
                                               .username(client.getUsername()).password(client.getPassword()).build();
                        staffRepository.save(officeEmp);
                        clientRepository.deleteById(client.getId());
                        return;
                }
            }
            clientRepository.save(client);
        });
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    private boolean userIsClient(User user) {
        return user.getRoles().stream().anyMatch(role -> role.getName().equals(UserRole.CLIENT));
    }


    private ClientDto mapToDTO(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setUsername(client.getUsername());
        clientDto.setId(client.getId());
        return clientDto;
    }

    @Override
    public ClientDto getClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        return clientOptional.map(this::mapToDTO).orElse(null);
    }

}
