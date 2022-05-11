package com.example.ticketShop.dto.address;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressDto {

    @NotEmpty
    private String street;

    @NotEmpty
    private String city;

    @NotEmpty
    private String number;

    @NotEmpty
    private String postalCode;

    @NotEmpty
    private String country;
}
