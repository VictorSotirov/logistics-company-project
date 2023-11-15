package nbu.bg.logisticscompany.service;

public interface CompanyService
{
    //Might need to add/refactor the functions using the company name instead of id
    void updateCompanyName(Long companyId, String newCompanyName);

    void updateCompanyAddress(Long companyId, String newCompanyAddress);

    void deleteCompany(Long companyId);
}
