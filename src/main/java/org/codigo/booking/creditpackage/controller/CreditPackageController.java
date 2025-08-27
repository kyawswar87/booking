package org.codigo.booking.creditpackage.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.codigo.booking.creditpackage.model.Country;
import org.codigo.booking.creditpackage.model.CreditPackage;
import org.codigo.booking.creditpackage.service.CreditPackageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/packages")
@SecurityRequirement(name = "bearerAuth")
public class CreditPackageController {
    private final CreditPackageService creditPackageService;

    public CreditPackageController(CreditPackageService creditPackageService) {
        this.creditPackageService = creditPackageService;
    }

    @GetMapping
    public List<CreditPackage> findAll() {
        return creditPackageService.findAll();
    }

    @GetMapping("/{id}")
    public CreditPackage findById(@PathVariable Long id) {
        return creditPackageService.findById(id);
    }

    @GetMapping("/country/{country}")
    public CreditPackage findByCountry(@PathVariable Country country) {
        return creditPackageService.findByCountry(country);
    }

    @PostMapping
    public CreditPackage create(@RequestBody CreditPackage creditPackage) {
        return creditPackageService.save(creditPackage);
    }

    @PutMapping("/{id}")
    public CreditPackage update(@PathVariable Long id, @RequestBody CreditPackage creditPackage) {
        creditPackage.setId(id);
        return creditPackageService.update(creditPackage);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        creditPackageService.delete(id);
    }
}
