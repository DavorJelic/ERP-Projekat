package com.example.ticketShop.service;

import com.example.ticketShop.dto.address.CreateAddressDto;
import com.example.ticketShop.entity.Address;
import com.example.ticketShop.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    @Transactional
    public Address findAddress(CreateAddressDto addressDto) {
        return addressRepository.findByStreetAndNumberAndCountryAndCityAndPostalCode(addressDto.getStreet(), addressDto.getNumber(), addressDto.getCountry(), addressDto.getCity(), addressDto.getPostalCode());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public Address create(CreateAddressDto createAddress) {
        Address address = findAddress(createAddress);
        if (address == null) {
            address = new Address(createAddress.getStreet(), createAddress.getCity(), createAddress.getNumber(), createAddress.getPostalCode(), createAddress.getCountry());
            addressRepository.save(address);
        }
        return address;
    }
}
