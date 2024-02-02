package nbu.bg.logisticscompany.service.impl;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.exceptions.CompanyAlreadyExistsException;
import nbu.bg.logisticscompany.exceptions.CompanyNotFoundException;
import nbu.bg.logisticscompany.model.dto.CompanyDto;
import nbu.bg.logisticscompany.model.entity.Company;
import nbu.bg.logisticscompany.repository.CompanyRepository;
import nbu.bg.logisticscompany.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService
{
    private final CompanyRepository companyRepository;

    private  final EntityManager entityManager;

    @Override
    public void createCompany(CompanyDto companyToCreate) throws CompanyAlreadyExistsException
    {
        if(companyRepository.count() > 0)
        {
            throw new CompanyAlreadyExistsException("A company already exists. Cannot create a second one.");
        }

        Company company = Company.builder()
                .name(companyToCreate.getName())
                .address(companyToCreate.getAddress()).build();

        companyRepository.save(company);
    }

    @Override
    public void updateCompany(Long companyId, CompanyDto companyToUpdate) throws IllegalArgumentException
    {
        if (companyId == null || companyToUpdate == null)
        {
            throw new IllegalArgumentException("Invalid company");
        }

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + companyId + " does not exist."));

        company.setName(companyToUpdate.getName());

        company.setAddress(companyToUpdate.getAddress());

        companyRepository.save(company);
    }

    @Override
    public Optional<CompanyDto> getCompanyData()
    {
        Optional<Company> companyOptional = companyRepository.findFirstCompany();

        return companyOptional.map(company ->
                CompanyDto.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .address(company.getAddress())
                        .build());
    }

    //THIS DELETES ALL RECORDS OF ALL TABLES
    @Override
    @Transactional
    public void deleteCompany() throws CompanyNotFoundException
    {
        Optional<Company> company = companyRepository.findFirstCompany();

        if (company.isEmpty())
        {
            throw new CompanyNotFoundException("Invalid company");
        }

        //ONLY THING LEFT FOR REFACTORING
        //NEED TO REFACTOR BASED ON HOW USERS ARE HANDLED AND TO CHECK IF OFFICES ARE DELETED
        entityManager.createNativeQuery("DELETE FROM Company").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM Orders").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM Staff").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM Client").executeUpdate();
    }

    @Override
    public boolean dbHasCompany()
    {
        return companyRepository.count() > 0;
    }
}
