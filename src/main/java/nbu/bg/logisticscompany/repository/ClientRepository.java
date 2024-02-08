package nbu.bg.logisticscompany.repository;

import nbu.bg.logisticscompany.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Client repository.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<Client> findByUsername(String username);
}
