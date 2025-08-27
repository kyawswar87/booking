package org.codigo.booking.creditpackage.repository;

import org.codigo.booking.creditpackage.model.CreditPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditPackageRepository extends JpaRepository<CreditPackage, Long> {
}
