package com.example.ticketShop.repository;

import com.example.ticketShop.entity.InvoiceTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceTicketRepository extends JpaRepository<InvoiceTicket, String> {

}
