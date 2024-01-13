package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.model.dto.CompanyDto;
import nbu.bg.logisticscompany.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class CompanyController
{
    private final CompanyService companyService;

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

            return "editCompany";
        }
        else
        {
            return "redirect:/";
        }
    }

    //TODO
    @PostMapping("/edit")
    public String updateCompanyData(@RequestParam Long companyId, @ModelAttribute CompanyDto companyDto)
    {
        companyService.updateCompany(companyId, companyDto);

        return "redirect:/company/edit";
    }
}
