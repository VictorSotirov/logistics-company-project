package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.exceptions.CompanyAlreadyExistsException;
import nbu.bg.logisticscompany.model.dto.CompanyDto;

import java.util.Optional;

/**
 * The interface Company service.
 */
public interface CompanyService {
    /**
     * Create company.
     *
     * @param companyToCreate the company to create
     * @throws CompanyAlreadyExistsException the company already exists exception
     */
    void createCompany(CompanyDto companyToCreate) throws CompanyAlreadyExistsException;

    /**
     * Update company.
     *
     * @param companyId  the company id
     * @param companyDto the company dto
     */
    void updateCompany(Long companyId, CompanyDto companyDto);

    /**
     * Delete company.
     */
    void deleteCompany();

    /**
     * Gets company data.
     *
     * @return the company data
     */
    Optional<CompanyDto> getCompanyData();

    /**
     * Db has company boolean.
     *
     * @return the boolean
     */
    public boolean dbHasCompany();
}
