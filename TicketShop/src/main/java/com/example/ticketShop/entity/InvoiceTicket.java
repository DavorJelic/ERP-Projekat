package com.example.ticketShop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoices_tickets")
public class InvoiceTicket extends CreatedBase{

    @ManyToOne
    @JoinColumn(name = "fk_it_ticketType")
    private TicketType ticketType;

    @ManyToOne
    @JoinColumn(name = "fk_it_invoice")
    private Invoice invoice;

    @Column
    private Integer quantity;
}
