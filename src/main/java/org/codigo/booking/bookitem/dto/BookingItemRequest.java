package org.codigo.booking.bookitem.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingItemRequest {
    private Long clazzId;
    private int userId;
}
