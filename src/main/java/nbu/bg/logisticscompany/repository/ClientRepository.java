package nbu.bg.logisticscompany.repository;

import nbu.bg.logisticscompany.model.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
}
