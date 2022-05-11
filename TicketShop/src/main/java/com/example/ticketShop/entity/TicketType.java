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
@Table(name = "ticket_types")
public class TicketType extends CreatedBase{

    @Column
    private String typeName;

    @Column
    private Long price;

    @Column
    private int numberOfCards;

    @ManyToOne
    @JoinColumn(name = "fk_ticket_type_event")
    private Event event;
}
