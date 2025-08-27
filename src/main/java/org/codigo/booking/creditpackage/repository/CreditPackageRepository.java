package org.codigo.booking.creditpackage.repository;

import org.codigo.booking.creditpackage.model.Country;
import org.codigo.booking.creditpackage.model.CreditPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditPackageRepository extends JpaRepository<CreditPackage, Long> {
    Optional<CreditPackage> findByCountry(Country country);
}
