package com.example.ticketShop.entity;

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
@Table(name = "tickets")
public class Ticket extends CreatedBase{

    @ManyToOne
    @JoinColumn(name = "fk_ticket_event")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "fk_ticket_ticket_type")
    private TicketType type;

    @ManyToOne
    @JoinColumn(name = "fk_ticket_user")
    private User user;
}
