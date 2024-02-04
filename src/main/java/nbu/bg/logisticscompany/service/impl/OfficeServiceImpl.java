package nbu.bg.logisticscompany.service.impl;

import lombok.AllArgsConstructor;
import  lombok.NoArgsConstructor;
import nbu.bg.logisticscompany.model.entity.Company;
import nbu.bg.logisticscompany.model.entity.Office;
import nbu.bg.logisticscompany.model.dto.OfficeDto;
import nbu.bg.logisticscompany.repository.CompanyRepository;
import nbu.bg.logisticscompany.repository.OfficeRepository;
import nbu.bg.logisticscompany.service.OfficeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;
    private final CompanyRepository companyRepository;


    @Override
    public List<OfficeDto> getAllOffices() {
        List<Office> offices = officeRepository.findAll();
        return offices.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OfficeDto> getOfficesByCompanyId(Long companyId) {
        List<Office> offices = officeRepository.findByCompanyId(companyId);
        return offices.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addOffice(OfficeDto officeDto) {
        Office office = mapToEntity(officeDto);
        officeRepository.save(office);
    }

    @Override
    public OfficeDto getOfficeById(Long id) {
        Optional<Office> officeOptional = officeRepository.findById(id);
        return officeOptional.map(this::mapToDTO).orElse(null);
    }

    @Override
    public void updateOffice(Long id, OfficeDto updatedOfficeDto) {
        Optional<Office> officeOptional = officeRepository.findById(id);
        officeOptional.ifPresent(office -> {
            office.setAddress(updatedOfficeDto.getAddress());
            Company company = companyRepository.getReferenceById(updatedOfficeDto.getCompanyId());

            office.setCompany(company);
            officeRepository.save(office);
        });
    }

    @Override
    public void deleteOffice(Long id) {
        officeRepository.deleteById(id);
    }

    private OfficeDto mapToDTO(Office office) {
        OfficeDto officeDto = new OfficeDto();
        officeDto.setAddress(office.getAddress());
        officeDto.setId(office.getId());

        Company company = companyRepository.getReferenceById(office.getCompany().getId());

        officeDto.setCompanyId(company.getId());
        System.out.println(officeDto);
        return officeDto;
    }

    private Office mapToEntity(OfficeDto officeDto) {
        Office office = new Office();
        office.setAddress(officeDto.getAddress());
        office.setId(officeDto.getId());

        Company company = companyRepository.getReferenceById(officeDto.getCompanyId());

        office.setCompany(company);
        return office;
    }

}
