package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.OrderDto;

import java.util.List;

public interface ClientService {
    public List<OrderDto> getReceivedOrders(Long id);

    public List<OrderDto> getSentOrders(Long id);
}
