package nbu.bg.logisticscompany.service.impl;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.exceptions.CompanyNotFoundException;
import nbu.bg.logisticscompany.model.entity.Company;
import nbu.bg.logisticscompany.repository.CompanyRepository;
import nbu.bg.logisticscompany.service.CompanyService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService
{
    private final CompanyRepository companyRepository;

    //Update the company name by first acquiring it from the db
    @Override
    public void updateCompanyName(Long companyId, String newCompanyName)
    {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Selected company does not exist."));

        if (company != null)
        {
            company.setName(newCompanyName);

            companyRepository.save(company);
        }
    }

    //Update the company address by first acquiring it from the db
    @Override
    public void updateCompanyAddress(Long companyId, String newCompanyAddress)
    {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Selected company does not exist."));

        if (company != null)
        {
            company.setAddress(newCompanyAddress);

            companyRepository.save(company);
        }
    }

    //Delete the company from the db based on its id
    @Override
    public void deleteCompany(Long companyId)
    {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Selected company does not exist."));

        if (company != null)
        {
            companyRepository.deleteById(companyId);
        }
    }
}
