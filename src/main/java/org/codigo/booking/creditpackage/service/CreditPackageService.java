package org.codigo.booking.creditpackage.service;

import org.codigo.booking.creditpackage.model.Country;
import org.codigo.booking.creditpackage.model.CreditPackage;
import org.codigo.booking.creditpackage.repository.CreditPackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditPackageService {
    private final CreditPackageRepository creditPackageRepository;

    public CreditPackageService(CreditPackageRepository creditPackageRepository) {
        this.creditPackageRepository = creditPackageRepository;
    }

    public CreditPackage save(CreditPackage creditPackage) {
        creditPackage.setId(null);
        return creditPackageRepository.save(creditPackage);
    }

    public CreditPackage update(CreditPackage creditPackage) {
        return creditPackageRepository.save(creditPackage);
    }

    public CreditPackage findById(Long id) {
        return creditPackageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id is null."));
    }

    public CreditPackage findByCountry(Country country) {
        return creditPackageRepository.findByCountry(country).orElseThrow(() -> new IllegalArgumentException("Packages by country do not found"));
    }

    public List<CreditPackage> findAll() {
        return creditPackageRepository.findAll();
    }

    public void delete(Long id) {
        creditPackageRepository.deleteById(id);
    }
}
