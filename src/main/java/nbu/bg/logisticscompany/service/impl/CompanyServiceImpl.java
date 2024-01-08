package nbu.bg.logisticscompany.service.impl;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.exceptions.CompanyNotFoundException;
import nbu.bg.logisticscompany.model.dto.CompanyDto;
import nbu.bg.logisticscompany.model.entity.Company;
import nbu.bg.logisticscompany.model.entity.Office;
import nbu.bg.logisticscompany.repository.CompanyRepository;
import nbu.bg.logisticscompany.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService
{
    private final CompanyRepository companyRepository;

    //Method for creating a company if the task required it

    @Override
    public void updateCompany(Long companyId, CompanyDto companyToUpdate)
    {
        if (companyId == null || companyToUpdate == null)
        {
            throw new IllegalArgumentException("Invalid company");
        }

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + companyId + " does not exist."));

        company.setName(companyToUpdate.getCompanyName());

        company.setAddress(companyToUpdate.getCompanyAddress());

        companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long companyId)
    {
        if (companyId == null)
        {
            throw new IllegalArgumentException("Invalid company");
        }

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + companyId + " does not exist."));

        companyRepository.deleteById(companyId);
    }

    @Override
    public Optional<CompanyDto> getCompanyData()
    {
        return StreamSupport.stream(companyRepository.findAll().spliterator(), false)
                .findFirst()
                .map(company -> new CompanyDto(company.getName(), company.getAddress()));
    }


    //public List<Office> getAllOfficesByCompanyId(Long companyId) Should persist in officeService or be implemented here

}
