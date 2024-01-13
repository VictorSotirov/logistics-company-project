package nbu.bg.logisticscompany.repository;

import nbu.bg.logisticscompany.model.entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> /*might replace with JpaRepository*/
{
    //Get company from db using its id
    Optional<Company> findById(Long id);
}
