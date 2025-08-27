package org.codigo.booking.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasedItemRequest {
    private Long creditPackageId;
    private int userId;
}
