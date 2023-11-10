package nbu.bg.logisticscompany.repository;

import nbu.bg.logisticscompany.model.entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long>
{
    //Getting a company from the db by ID
    Optional<Company> findById(Long id);

    //Getting a company/ies from the db by name
    List<Company> findByName(String companyName);

}
