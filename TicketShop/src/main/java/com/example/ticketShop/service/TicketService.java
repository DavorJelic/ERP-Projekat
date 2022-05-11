package com.example.ticketShop.service;

import com.example.ticketShop.constants.ErrorCodes;
import com.example.ticketShop.dto.ticketType.BuyTicketDto;
import com.example.ticketShop.entity.Event;
import com.example.ticketShop.entity.Invoice;
import com.example.ticketShop.entity.InvoiceTicket;
import com.example.ticketShop.entity.Ticket;
import com.example.ticketShop.entity.TicketType;
import com.example.ticketShop.entity.User;
import com.example.ticketShop.repository.TicketRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final EventService eventService;
    private final TicketTypeService ticketTypeService;
    private final InvoiceTicketService invoiceTicketService;
    private final InvoiceService invoiceService;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public Invoice buyTickets(Invoice invoice, User user, List<BuyTicketDto> buyTicketsDto) {
        List<Ticket> tickets = new ArrayList<>();
        List<InvoiceTicket> invoiceTickets = new ArrayList<>();
        buyTicketsDto.forEach(buyTicketDto -> {
            Event event = eventService.findById(buyTicketDto.getEvent(), ErrorCodes.EVENT_NOT_FOUND_6);
            TicketType ticketType = ticketTypeService.findById(buyTicketDto.getTicketType(), ErrorCodes.TICKET_TYPE_NOT_FOUND);
            for (int i = 0; i<buyTicketDto.getNumber(); i++) {
                tickets.add(new Ticket(event, ticketType, user));
            }
            invoiceTickets.add(new InvoiceTicket(ticketType, invoice, buyTicketDto.getNumber()));
        });

        invoiceTicketService.create(invoiceTickets);
        ticketRepository.saveAll(tickets);

        return invoiceService.findById(invoice.getUuid());
    }
}
