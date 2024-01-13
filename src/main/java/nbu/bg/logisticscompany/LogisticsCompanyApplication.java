package nbu.bg.logisticscompany;

import nbu.bg.logisticscompany.repository.CompanyRepository;
import nbu.bg.logisticscompany.service.impl.CompanyServiceImpl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class LogisticsCompanyApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(LogisticsCompanyApplication.class, args);
    }

}

