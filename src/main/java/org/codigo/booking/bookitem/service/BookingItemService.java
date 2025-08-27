package org.codigo.booking.bookitem.service;

import org.codigo.booking.bookitem.dto.BookingItemRequest;
import org.codigo.booking.bookitem.model.BookingItem;
import org.codigo.booking.bookitem.model.Status;
import org.codigo.booking.bookitem.repository.BookingItemRepository;
import org.codigo.booking.clazz.model.Clazz;
import org.codigo.booking.clazz.service.ClazzService;
import org.codigo.booking.purchase.model.PurchasedItem;
import org.codigo.booking.purchase.service.PurchasedItemService;
import org.codigo.booking.user.model.User;
import org.codigo.booking.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingItemService {

    private final BookingItemRepository bookingItemRepository;
    private final PurchasedItemService  purchasedItemService;
    private final UserService userService;
    private final ClazzService clazzService;

    public BookingItemService(BookingItemRepository bookingItemRepository,
                              UserService userService,
                              ClazzService clazzService,
                              PurchasedItemService purchasedItemService) {
        this.bookingItemRepository = bookingItemRepository;
        this.purchasedItemService = purchasedItemService;
        this.clazzService = clazzService;
        this.userService = userService;
    }

    @Transactional
    public BookingItem book(BookingItemRequest request) {

        // add booking no in the class
        Clazz clazz = clazzService.addMembers(request.getClazzId(), 1);

        var user = userService.findUserById(request.getUserId());
        // deduct balance from user purchase item
        var purchasedItem = purchasedItemService.findByUserId(user.getId());
        purchasedItemService.deductCreditBalance(purchasedItem.getId(), clazz.getCreditsPrice());

        var bookingItem = BookingItem.builder()
                            .clazz(clazz)
                            .user(user)
                            .status(Status.BOOKED).build();
        return bookingItemRepository.save(bookingItem);
    }

    @Transactional
    public BookingItem cancel(BookingItemRequest request, Long bookingId) {

        var bookingItem = findById(bookingId);

        // check clazz start time to be cancelled
        if(clazzService.isCancellable(request.getClazzId()))
            throw new IllegalArgumentException("Class can't be cancelled");

        var clazz = clazzService.removeMembers(request.getClazzId(), 1);
        var user = userService.findUserById(request.getUserId());
        // revert balance to user purchase item
        var purchasedItem = purchasedItemService.findByUserId(user.getId());
        purchasedItemService.addCreditBalance(purchasedItem.getId(), clazz.getCreditsPrice());

        bookingItem.setStatus(Status.CANCELLED);
        return bookingItemRepository.save(bookingItem);
    }

    public BookingItem findById(Long id) {
        return bookingItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id not found"));
    }

    public List<BookingItem> findAll() {
        return bookingItemRepository.findAll();
    }
}
