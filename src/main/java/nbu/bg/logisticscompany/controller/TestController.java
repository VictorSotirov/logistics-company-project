package nbu.bg.logisticscompany.controller;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.annotation.security.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/test")
@AllArgsConstructor
public class TestController {
    @isStaff
    @GetMapping("/staff")
    public ResponseEntity<String> staffZone() {
        return ResponseEntity.ok("Success!");
    }

    @isAdmin
    @GetMapping("/admin")
    public ResponseEntity<String> adminZone() {
        return ResponseEntity.ok("Success!");
    }

    @isClient
    @GetMapping("/client")
    public ResponseEntity<String> clientZone(Authentication authentication) {
        return ResponseEntity.ok("Success!");
    }

    @isCourier
    @GetMapping("/courier")
    public ResponseEntity<String> courierZone() {
        return ResponseEntity.ok("Success!");
    }

    @isOfficeEmployee
    @GetMapping("/office-employee")
    public ResponseEntity<String> officeEmployeeZone() {
        return ResponseEntity.ok("Success!");
    }
}
