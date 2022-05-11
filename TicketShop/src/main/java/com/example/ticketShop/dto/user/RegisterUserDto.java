package com.example.ticketShop.dto.user;

import com.example.ticketShop.enums.UserType;
import javax.validation.constraints.NotEmpty;
import com.example.ticketShop.constants.RegexPatterns;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

    @NotEmpty
    private String name;

    @NotEmpty
    @Pattern(regexp = RegexPatterns.EMAIL, message = "Invalid email address")
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private UserType userType;

    @NotEmpty
    private String street;

    @NotEmpty
    private String number;

    @NotEmpty
    private String city;

    @NotEmpty
    private String postalCode;

    @NotEmpty
    private String country;
}
