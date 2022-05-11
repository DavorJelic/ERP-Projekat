package com.example.ticketShop.repository;

import com.example.ticketShop.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {

        Address findByStreetAndNumberAndCountryAndCityAndPostalCode(String street, String number, String country, String city, String postalCode);
}
