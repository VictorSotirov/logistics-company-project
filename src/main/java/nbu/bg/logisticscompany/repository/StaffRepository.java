package nbu.bg.logisticscompany.repository;

import nbu.bg.logisticscompany.model.entity.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {
}
