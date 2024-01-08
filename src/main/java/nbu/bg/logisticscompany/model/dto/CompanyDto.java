package nbu.bg.logisticscompany.model.dto;

public class CompanyDto
{
    private String companyName;
    private String companyAddress;


    public CompanyDto()
    {
    }

    public CompanyDto(String companyName, String companyAddress) throws IllegalArgumentException
    {
        setCompanyName(companyName);
        setCompanyAddress(companyAddress);
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }


    public void setCompanyName(String companyName) throws IllegalArgumentException
    {
        if (companyName.isBlank() || companyName == null)
        {
            throw new IllegalArgumentException();
        }

        this.companyName = companyName;
    }

    public void setCompanyAddress(String companyAddress)
    {
        if (companyAddress.isBlank() || companyAddress == null)
        {
            throw new IllegalArgumentException();
        }

        this.companyAddress = companyAddress;
    }
}
