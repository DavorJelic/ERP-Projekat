package com.example.ticketShop.dto.user;

import com.example.ticketShop.constants.RegexPatterns;
import javax.validation.constraints.Pattern;

public class UpdateUserDto {

    private String name;

    @Pattern(regexp = RegexPatterns.EMAIL, message = "Invalid email address")
    private String email;

    private String password;

    private String phoneNumber;

    private String userType;
}
