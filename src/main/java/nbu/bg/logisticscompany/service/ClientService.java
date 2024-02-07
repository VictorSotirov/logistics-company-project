package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.ClientDto;
import nbu.bg.logisticscompany.model.dto.OfficeDto;
import nbu.bg.logisticscompany.model.dto.OrderDto;

import java.util.List;

public interface ClientService {
    public List<OrderDto> getReceivedOrders(Long id);

    public List<OrderDto> getSentOrders(Long id);

    List<ClientDto> getAllClients();

    void updateClient(Long id, ClientDto updateClientDto);

    void deleteClient(Long id);

    ClientDto getClientById(Long id);
}
