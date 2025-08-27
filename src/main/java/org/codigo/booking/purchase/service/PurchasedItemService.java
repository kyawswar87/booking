package org.codigo.booking.purchase.service;

import org.codigo.booking.purchase.model.PurchasedItem;
import org.codigo.booking.purchase.repository.PurchasedItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasedItemService {
    private final PurchasedItemRepository purchasedItemRepository;

    public PurchasedItemService(PurchasedItemRepository purchasedItemRepository) {
        this.purchasedItemRepository = purchasedItemRepository;
    }

    public PurchasedItem findByUserId(int id) {
        return purchasedItemRepository.findByUserId(id).orElseThrow(() -> new IllegalArgumentException("user id not found"));
    }

    public PurchasedItem findById(Long id) {
        return purchasedItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found"));
    }

    public List<PurchasedItem> findAll() {
        return purchasedItemRepository.findAll();
    }

    public PurchasedItem save(PurchasedItem purchasedItem) {
        purchasedItem.setId(null);
        purchasedItem.setCreditBalance(purchasedItem.getCreditPackage().getCreditPoint());
        return purchasedItemRepository.save(purchasedItem);
    }

    public PurchasedItem addCreditBalance(Long purchasedItemId, int credit) {
        var item = purchasedItemRepository.findById(purchasedItemId).orElseThrow(() -> new IllegalArgumentException("PurchasedItem id not found"));
        item.setCreditBalance(item.getCreditBalance()+credit);
        return purchasedItemRepository.save(item);
    }

    public PurchasedItem deductCreditBalance(Long purchasedItemId, int credit) {
        var item = purchasedItemRepository.findById(purchasedItemId).orElseThrow(() -> new IllegalArgumentException("PurchasedItem id not found"));
        if(item.getCreditBalance()-credit<0) {
            throw new IllegalArgumentException("Credit balance not enough");
        }
        item.setCreditBalance(item.getCreditBalance()-credit);
        return purchasedItemRepository.save(item);
    }

    public void delete(PurchasedItem purchasedItem) {
        purchasedItemRepository.delete(purchasedItem);
    }
}
