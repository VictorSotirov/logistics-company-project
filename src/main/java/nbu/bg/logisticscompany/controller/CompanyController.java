package nbu.bg.logisticscompany.controller;

import nbu.bg.logisticscompany.model.dto.CompanyDto;
import nbu.bg.logisticscompany.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController
{
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService)
    {
        this.companyService = companyService;
    }

    @GetMapping("/company")
    public ResponseEntity<CompanyDto> getCompanyData()
    {
        Optional<CompanyDto> companyDtoOptional = companyService.getCompanyData();

        return companyDtoOptional
                .map(companyDto -> new ResponseEntity<>(companyDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/edit")
    public String editCompanyData(Model model)
    {
        Optional<CompanyDto> companyDtoOptional = companyService.getCompanyData();

        if (companyDtoOptional.isPresent())
        {
            model.addAttribute("company", companyDtoOptional.get());

            return "editCompany"; // This is the name of the HTML page for editing company data
        }
        else
        {
            return "redirect:/"; // Redirect to home page or handle as needed
        }
    }

    @PostMapping("/edit")
    public String updateCompanyData(@RequestParam Long companyId, @ModelAttribute CompanyDto companyDto)
    {
        // Update the company data using the provided CompanyDto
        companyService.updateCompany(companyId, companyDto);

        // Redirect to the view page or handle as needed
        return "redirect:/company/edit";
    }
}
