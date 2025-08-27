package org.codigo.booking.bookitem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.codigo.booking.bookitem.dto.BookingItemRequest;
import org.codigo.booking.bookitem.model.BookingItem;
import org.codigo.booking.bookitem.service.BookingItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class BookingItemController {

    private final BookingItemService bookingItemService;

    public BookingItemController(BookingItemService bookingItemService) {
        this.bookingItemService = bookingItemService;
    }

    @PostMapping("/book")
    public BookingItem book(@RequestBody BookingItemRequest request) {
        return bookingItemService.book(request);
    }

    @PutMapping("/cancel/{id}")
    public BookingItem cancel(@RequestBody BookingItemRequest request, @PathVariable Long id) {
        return bookingItemService.cancel(request, id);
    }
}
