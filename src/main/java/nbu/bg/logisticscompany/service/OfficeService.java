package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.entity.Office;

import java.util.List;

public interface OfficeService {

    List<Office> getAllOffices();

    List<Office> getOfficesByCompanyId(Long companyId);

    void addOffice(Office office);

    Office getOfficeById(Long id);

    void updateOffice(Long id, Office updateOffice);

    void deleteOffice(Long id);
}
