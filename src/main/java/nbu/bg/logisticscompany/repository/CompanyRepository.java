package nbu.bg.logisticscompany.repository;

import nbu.bg.logisticscompany.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>
{
    Optional<Company> findById(Long id);

    boolean existsByName(String companyName);
}
