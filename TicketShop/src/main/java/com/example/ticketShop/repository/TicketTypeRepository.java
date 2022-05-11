package com.example.ticketShop.repository;

import com.example.ticketShop.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, String> {

    //boolean existsByName(String typeName);
}
