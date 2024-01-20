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
        if(companyRepository.existsByName(companyToCreate.getName()))
        {
            throw new CompanyAlreadyExistsException("Company with name"
                    + companyToCreate.getName() + " already exists.");
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
        Optional<Company> companyOptional = companyRepository.findById(1L);

        return companyOptional.map(company ->
                CompanyDto.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .address(company.getAddress())
                        .build());
    }

    //WARNING: THIS DELETES ALL RECORDS OF ALL TABLES
    @Override
    @Transactional
    public void deleteCompany(Long companyId) throws CompanyNotFoundException
    {
        if (companyId == null || companyRepository.findById(companyId).isEmpty())
        {
            throw new CompanyNotFoundException("Invalid company");
        }

        entityManager.createNativeQuery("DELETE FROM Office").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM Company").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM Role").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM User").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM Orders").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM Staff").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM Client").executeUpdate();
    }
}
