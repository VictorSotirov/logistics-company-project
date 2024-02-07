package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.OfficeDto;

import java.util.List;

public interface OfficeService {

    List<OfficeDto> getAllOffices();

    List<OfficeDto> getOfficesByCompanyId(Long companyId);

    void addOffice(OfficeDto office);

    OfficeDto getOfficeById(Long id);

    void updateOffice(Long id, OfficeDto updateOffice);

    void deleteOffice(Long id);
}
