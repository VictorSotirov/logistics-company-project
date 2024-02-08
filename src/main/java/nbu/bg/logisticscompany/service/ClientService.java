package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.ClientDto;
import nbu.bg.logisticscompany.model.dto.OfficeDto;
import nbu.bg.logisticscompany.model.dto.OrderDto;

import java.util.List;

/**
 * The interface Client service.
 */
public interface ClientService {
    /**
     * Gets received orders.
     *
     * @param id the id
     * @return the received orders
     */
    public List<OrderDto> getReceivedOrders(Long id);

    /**
     * Gets sent orders.
     *
     * @param id the id
     * @return the sent orders
     */
    public List<OrderDto> getSentOrders(Long id);

    /**
     * Gets all clients.
     *
     * @return the all clients
     */
    List<ClientDto> getAllClients();

    /**
     * Update client.
     *
     * @param id              the id
     * @param updateClientDto the update client dto
     */
    void updateClient(Long id, ClientDto updateClientDto);

    /**
     * Delete client.
     *
     * @param id the id
     */
    void deleteClient(Long id);

    /**
     * Gets client by id.
     *
     * @param id the id
     * @return the client by id
     */
    ClientDto getClientById(Long id);
}
