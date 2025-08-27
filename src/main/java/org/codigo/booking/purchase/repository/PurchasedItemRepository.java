package org.codigo.booking.purchase.repository;

import org.codigo.booking.purchase.model.PurchasedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchasedItemRepository extends JpaRepository<PurchasedItem, Long> {
    Optional<PurchasedItem> findByUserId(Integer user_id);
}
