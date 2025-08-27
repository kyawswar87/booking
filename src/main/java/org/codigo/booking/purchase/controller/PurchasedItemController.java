package org.codigo.booking.purchase.controller;

import org.codigo.booking.purchase.dto.PurchasedItemRequest;
import org.codigo.booking.purchase.model.PurchasedItem;
import org.codigo.booking.purchase.service.PurchasedItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchasedItemController {

    private final PurchasedItemService purchasedItemService;

    public PurchasedItemController(PurchasedItemService purchasedItemService) {
        this.purchasedItemService = purchasedItemService;
    }

    @GetMapping
    public List<PurchasedItem> findAll() {
        return purchasedItemService.findAll();
    }

    @GetMapping("/{id}")
    public PurchasedItem findById(@PathVariable Long id) {
        return purchasedItemService.findById(id);
    }

    @PostMapping
    public PurchasedItem purchase(@RequestBody PurchasedItemRequest request) {
        return purchasedItemService.purchasedItem(request);
    }
}
