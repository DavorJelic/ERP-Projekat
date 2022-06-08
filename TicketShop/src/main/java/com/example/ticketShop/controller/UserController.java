package com.example.ticketShop.controller;

import com.example.ticketShop.constants.ApiConstants;
import com.example.ticketShop.constants.ErrorCodes;
import com.example.ticketShop.dto.ticketType.BuyTicketDto;
import com.example.ticketShop.dto.user.RegisterUserDto;
import com.example.ticketShop.dto.user.UpdateUserDto;
import com.example.ticketShop.dto.user.UserResponseDto;
import com.example.ticketShop.entity.User;
import com.example.ticketShop.mapper.UserMapper;
import com.example.ticketShop.service.UserService;
import com.example.ticketShop.util.ResponseEntityUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_V1_USER)
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    // region CRUD
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> register(@RequestBody final RegisterUserDto registerUser) {
        final User user = userService.register(registerUser);

        return ResponseEntityUtil.buildCreatedEntityResponse(ApiConstants.API_V1_USER, userMapper.mapToResponse(user));
    }

    @GetMapping(value = "/{uuid}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> findById(@RequestParam String uuid) {
        final User user = userService.findById(uuid, ErrorCodes.USER_NOT_FOUND_BY_ID_2);

        return ResponseEntity.ok(userMapper.mapToResponse(user));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponseDto>> findAll() {
        final List<User> users = userService.findAll();

        return ResponseEntity.ok(userMapper.mapToResponse(users));
    }

    @PutMapping(value = "/{uuid}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateById(@PathVariable("uuid") final String uuid, @RequestBody final UpdateUserDto updateUser) {
        userService.updateById(uuid, updateUser);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{uuid}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(@PathVariable("uuid") final String uuid) {
        userService.deleteById(uuid);

        return ResponseEntity.noContent().build();
    }
    // endregion

    // region Actions
    @PostMapping(value = "/{uuid}/buyTicket", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> buyTicket(@RequestParam("uuid") final String uuid,
                                          @RequestBody List<BuyTicketDto> buyTicketsDto) {
        userService.buyTickets(uuid, buyTicketsDto);

        return ResponseEntity.noContent().build();
    }
    // endregion


}
