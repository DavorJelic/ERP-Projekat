package com.example.ticketShop.dto.user;

import com.example.ticketShop.dto.base.CreatedDtoBase;
import com.example.ticketShop.entity.Address;
import com.example.ticketShop.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto extends CreatedDtoBase {

    private String name;

    private boolean verified;

    private String phoneNumber;

    private String email;

    private UserType userType;

    private Address address;
}
