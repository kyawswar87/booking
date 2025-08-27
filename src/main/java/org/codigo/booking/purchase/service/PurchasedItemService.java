package org.codigo.booking.purchase.service;

import org.codigo.booking.creditpackage.service.CreditPackageService;
import org.codigo.booking.purchase.dto.PurchasedItemRequest;
import org.codigo.booking.purchase.model.PurchasedItem;
import org.codigo.booking.purchase.repository.PurchasedItemRepository;
import org.codigo.booking.user.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchasedItemService {
    private final PurchasedItemRepository purchasedItemRepository;
    private final UserService userService;
    private final CreditPackageService  creditPackageService;

    public PurchasedItemService(PurchasedItemRepository purchasedItemRepository,
                                UserService userService,
                                CreditPackageService creditPackageService) {
        this.purchasedItemRepository = purchasedItemRepository;
        this.userService = userService;
        this.creditPackageService = creditPackageService;
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

    public PurchasedItem purchasedItem(PurchasedItemRequest request) {
        var creditPackage = creditPackageService.findById(request.getCreditPackageId());
        var user = userService.findUserById(request.getUserId());

        if(LocalDateTime.now().isAfter(creditPackage.getExpirationDate()))
            throw new IllegalArgumentException("The package is already expired");

        if(creditPackage.getCountry() != user.getCountry())
            throw new IllegalArgumentException("The user cannot buy creditPackage from different country");

        return purchasedItemRepository.save(
                PurchasedItem.builder()
                        .creditPackage(creditPackage)
                        .user(user)
                        .creditBalance(creditPackage.getCreditPoint())
                        .build()
        );
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
