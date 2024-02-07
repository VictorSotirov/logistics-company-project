package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.exceptions.CompanyAlreadyExistsException;
import nbu.bg.logisticscompany.model.dto.CompanyDto;

import java.util.Optional;

public interface CompanyService {
    void createCompany(CompanyDto companyToCreate) throws CompanyAlreadyExistsException;

    void updateCompany(Long companyId, CompanyDto companyDto);

    void deleteCompany();

    Optional<CompanyDto> getCompanyData();

    public boolean dbHasCompany();
}
