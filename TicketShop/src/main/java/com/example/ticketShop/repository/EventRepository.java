package com.example.ticketShop.repository;

import com.example.ticketShop.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {

    boolean existsByName(String name);
}
