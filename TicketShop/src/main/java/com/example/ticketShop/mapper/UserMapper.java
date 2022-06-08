package com.example.ticketShop.mapper;

import com.example.ticketShop.dto.user.RegisterUserDto;
import com.example.ticketShop.dto.user.UpdateUserDto;
import com.example.ticketShop.dto.user.UserResponseDto;
import com.example.ticketShop.entity.User;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public abstract class UserMapper {

    @Named("mapToResponse")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name")
    @Mapping(target = "phoneNumber")
    @Mapping(target = "email")
    @Mapping(target = "address")
    public abstract UserResponseDto mapToResponse(User user);

    @IterableMapping(qualifiedByName = "mapToResponse")
    public abstract List<UserResponseDto> mapToResponse(List<User> users);

    @Named("mapToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name")
    @Mapping(target = "phoneNumber")
    @Mapping(target = "email")
    public abstract User mapToEntity(RegisterUserDto registerUser);

    @Named("mapToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name")
    @Mapping(target = "phoneNumber")
    @Mapping(target = "email")
    @Mapping(target = "userType")
    public void mapToEntity(UpdateUserDto updateUser, @MappingTarget User user) {
    }
}
