package org.codigo.booking.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codigo.booking.creditpackage.model.Country;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String email;
    private String password;
    private String fullName;
    private Country country;
}
