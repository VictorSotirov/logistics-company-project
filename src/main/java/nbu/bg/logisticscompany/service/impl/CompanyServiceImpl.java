package nbu.bg.logisticscompany.service.impl;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.model.entity.Company;
import nbu.bg.logisticscompany.repository.CompanyRepository;
import nbu.bg.logisticscompany.service.CompanyService;
import org.springframework.stereotype.Service;

//Potential refactor to use the only available company
@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService
{
    private final CompanyRepository companyRepository;

    @Override
    public void updateCompanyName(Long companyId, String newCompanyName)
    {
        Company company = companyRepository.findById(companyId).orElse(null);

        if (company != null)
        {
            company.setName(newCompanyName);

            companyRepository.save(company);
        }
    }

    @Override
    public void updateCompanyAddress(Long companyId, String newCompanyAddress) {

    }

    @Override
    public void deleteCompanyById(Long companyId) {

    }

    @Override
    public void deleteCompanyByName(String name) {

    }
}
