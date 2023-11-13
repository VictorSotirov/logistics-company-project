package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.entity.Company;

public interface CompanyService
{
    void updateCompanyName(Long companyId, String newCompanyName);

    void updateCompanyAddress(Long companyId, String newCompanyAddress);

    void deleteCompany(Long companyId);
}
