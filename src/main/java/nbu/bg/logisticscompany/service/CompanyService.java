package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.CompanyDto;

import java.util.Optional;

public interface CompanyService
{
    //Might need to add/refactor the functions using the company name instead of id
    void updateCompany(Long companyId, CompanyDto companyDto);

    void deleteCompany(Long companyId);

    Optional<CompanyDto> getCompanyData();
}
