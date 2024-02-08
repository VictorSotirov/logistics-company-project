package nbu.bg.logisticscompany.repository;

import nbu.bg.logisticscompany.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Company repository.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findById(Long id);

    /**
     * Find first company optional.
     *
     * @return the optional
     */
//NATIVE QUERY TO GET THE FIRST COMPANY FROM THE DB SINCE THERE IS ONLY ONE BY DESIGN
    @Query("SELECT c FROM Company c ORDER BY c.id ASC")
    Optional<Company> findFirstCompany();

    /**
     * Exists by name boolean.
     *
     * @param companyName the company name
     * @return the boolean
     */
//CHECK IF THERE IS A COMPANY RECORD IN THE DB
    boolean existsByName(String companyName);
}
