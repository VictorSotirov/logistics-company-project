package nbu.bg.logisticscompany.service;

//Might need to move it to the matching repository class
//DA PITAM ZA KOETO
public interface CompanyService
{
    void updateCompanyName(Long companyId, String newCompanyName);

    void updateCompanyAddress(Long companyId, String newCompanyAddress);

    void deleteCompanyById(Long companyId);

    void deleteCompanyByName(String name);
}
