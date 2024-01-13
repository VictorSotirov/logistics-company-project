package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.CompanyDto;

import java.util.Optional;

public interface CompanyService
{
    //logic for deleting everything from db
    void createCompany(CompanyDto companyToCreate);

    void updateCompany(Long companyId, CompanyDto companyDto);

    void deleteCompany(Long companyId);

    Optional<CompanyDto> getCompanyData();
}
