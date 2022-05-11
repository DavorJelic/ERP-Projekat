package com.example.ticketShop.service;

import com.example.ticketShop.entity.InvoiceTicket;
import com.example.ticketShop.repository.InvoiceTicketRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceTicketService {

    private final InvoiceTicketRepository invoiceTicketRepository;

    public void create(List<InvoiceTicket> invoiceTickets) {
        invoiceTicketRepository.saveAll(invoiceTickets);
    }
}
