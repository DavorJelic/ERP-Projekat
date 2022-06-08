package com.example.ticketShop.service;

import com.example.ticketShop.constants.ErrorCodes;
import com.example.ticketShop.dto.address.CreateAddressDto;
import com.example.ticketShop.dto.ticketType.BuyTicketDto;
import com.example.ticketShop.dto.user.RegisterUserDto;
import com.example.ticketShop.dto.user.UpdateUserDto;
import com.example.ticketShop.entity.Invoice;
import com.example.ticketShop.entity.User;
import com.example.ticketShop.mapper.UserMapper;
import com.example.ticketShop.repository.UserRepository;
import com.example.ticketShop.exception.ConflictException;
import com.example.ticketShop.exception.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressService addressService;
    private final TicketService ticketService;
    private final InvoiceService invoiceService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public User findByName(String username, String errorCode) {
        return userRepository.findByName(username).orElseThrow(() -> {
            log.info("User with name '{}' does not exist.", username);
            throw new NotFoundException(errorCode);
        });
    }

    public User findById(String uuid, String errorCode) {
        return userRepository.findById(uuid).orElseThrow(() -> {
            log.info("User with id '{}' does not exist.", uuid);
            throw new NotFoundException(errorCode);
        });
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public User register(RegisterUserDto registerUser) {
        if (userRepository.existsByName(registerUser.getName())) {
            throw new ConflictException(ErrorCodes.USER_ALREADY_EXISTS);
        }

        User user = userMapper.mapToEntity(registerUser);
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        user.setAddress(addressService.create(new CreateAddressDto(registerUser.getStreet(), registerUser.getCity(), registerUser.getNumber(), registerUser.getPostalCode(), registerUser.getCountry())));

        user = userRepository.save(user);

        log.info("Successfully created {}.", user);
        return user;
    }

    public void buyTickets(String uuid, List<BuyTicketDto> buyTicketsDto) {
        User user = userRepository.findById(uuid).orElseThrow(() -> {throw new NotFoundException(ErrorCodes.EVENT_NOT_FOUND_7);});
        Invoice invoice = invoiceService.create(user);
        ticketService.buyTickets(invoice, user, buyTicketsDto);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void updateById(String uuid, UpdateUserDto updateUser) {
        User existingUser = findById(uuid, ErrorCodes.USER_NOT_FOUND_BY_ID_2);

        userMapper.mapToEntity(updateUser, existingUser);

        existingUser = userRepository.save(existingUser);

        log.info("Successfully updated {}.", existingUser);
    }

    @Transactional
    public void deleteById(String uuid) {
        User userForDeletion = findById(uuid, ErrorCodes.EVENT_NOT_FOUND_7);

        userRepository.deleteById(uuid);

        log.info("Successfully deleted {}.", userForDeletion);
    }
}
