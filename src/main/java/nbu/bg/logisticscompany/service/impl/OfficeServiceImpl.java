package nbu.bg.logisticscompany.service.impl;

import lombok.AllArgsConstructor;
import  lombok.NoArgsConstructor;
import nbu.bg.logisticscompany.model.entity.Office;
import nbu.bg.logisticscompany.repository.OfficeRepository;
import nbu.bg.logisticscompany.service.OfficeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;


    @Override
    public List<Office> getAllOffices() {
        return null;
    }

    @Override
    public List<Office> getOfficesByCompanyId(Long companyId) {
        return null;
    }

    @Override
    public void addOffice(Office office) {

    }

    @Override
    public Office getOfficeById(Long id) {
        return null;
    }

    @Override
    public void updateOffice(Long id, Office updateOffice) {

    }

    @Override
    public void deleteOffice(Long id) {

    }
}
