package nbu.bg.logisticscompany.service.impl;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.exceptions.CompanyAlreadyExistsException;
import nbu.bg.logisticscompany.exceptions.CompanyNotFoundException;
import nbu.bg.logisticscompany.model.dto.CompanyDto;
import nbu.bg.logisticscompany.model.entity.Company;
import nbu.bg.logisticscompany.repository.CompanyRepository;
import nbu.bg.logisticscompany.repository.OrderRepository;
import nbu.bg.logisticscompany.repository.UserRepository;
import nbu.bg.logisticscompany.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * The type Company service.
 */
@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    @Override
    public void createCompany(CompanyDto companyToCreate) throws CompanyAlreadyExistsException {
        //CHECKS IF THERE IS ALREADY A COMPANY IN THE DB AND THROWS EXCEPTION
        // SINCE THERE CAN ONLY BE ONE RECORD IN THE DB
        if (companyRepository.count() > 0) {
            throw new CompanyAlreadyExistsException("A company already exists. Cannot create a second one.");
        }

        //CREATES THE COMPANY USING THE DATA PASSED IN THE DTO
        Company company = Company.builder()
                .name(companyToCreate.getName())
                .address(companyToCreate.getAddress()).build();

        companyRepository.save(company);
    }

    @Override
    public void updateCompany(Long companyId, CompanyDto companyToUpdate) throws IllegalArgumentException {
        //IF THE ID OR THE DATA PASSED IS INVALID AND EXCEPTION IS THROWN
        if (companyId == null || companyToUpdate == null) {
            throw new IllegalArgumentException("Invalid company");
        }

        //SEARCHES FOR A EXISTING COMPANY WITH THE PASSED ID AND IF IT DOES NOT EXIST IT
        // THROWS EXCEPTION THAT SUCH COMPANY DOES NOT EXIST
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + companyId + " does not exist."));

        company.setName(companyToUpdate.getName());

        company.setAddress(companyToUpdate.getAddress());

        companyRepository.save(company);
    }

    @Override
    public Optional<CompanyDto> getCompanyData() {
        //GETS THE FIRST AND ONLY RECORD FROM THE COMPANY DB AND MAPS IT TO THE DTO
        // WHICH IS THEN RETURNED
        Optional<Company> companyOptional = companyRepository.findFirstCompany();

        return companyOptional.map(company ->
                CompanyDto.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .address(company.getAddress())
                        .build());
    }

    //THIS DELETES ALL RECORDS OF ALL TABLES SINCE IF THE ONLY COMPANY IS DELETED
    // EVERYTHING ELSE SHOULD BE ERASED
    @Override
    @Transactional
    public void deleteCompany() throws CompanyNotFoundException {
        Optional<Company> company = companyRepository.findFirstCompany();

        if (company.isEmpty()) {
            throw new CompanyNotFoundException("Invalid company");
        }

        companyRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();
    }

    //UTILITY METHOD TO CHECK IF THERE IS A COMPANY IN THE DB
    @Override
    public boolean dbHasCompany() {
        return companyRepository.count() > 0;
    }
}
