package nbu.bg.logisticscompany.service.impl;

import nbu.bg.logisticscompany.model.dto.CompanyDto;
import nbu.bg.logisticscompany.model.entity.Company;
import nbu.bg.logisticscompany.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {
    @InjectMocks
    private CompanyServiceImpl companyService;

    @Mock
    private CompanyRepository companyRepository;

    //TODO Finish rest of testing
    @Test
    void createCompany() {
    }

    @Test
    void updateCompany() {
    }

    @Test
    public void testGetCompanyData_Success() {

        Company company = Company.builder()
                .id(1L)
                .name("TestCompany")
                .address("TestAddress")
                .build();

        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        Optional<CompanyDto> result = companyService.getCompanyData();

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("TestCompany", result.get().getName());
        assertEquals("TestAddress", result.get().getAddress());

        verify(companyRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetCompanyData_NotFound() {
        when(companyRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<CompanyDto> result = companyService.getCompanyData();

        assertTrue(result.isEmpty());

        verify(companyRepository, times(1)).findById(1L);
    }


    @Test
    void deleteCompany() {
    }
}