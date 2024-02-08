package nbu.bg.logisticscompany.repository;

import nbu.bg.logisticscompany.model.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Office repository.
 */
@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {

    /**
     * Find by company id list.
     *
     * @param companyId the company id
     * @return the list
     */
    List<Office> findByCompanyId(Long companyId);

}
