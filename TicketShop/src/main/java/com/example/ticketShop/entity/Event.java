package com.example.ticketShop.entity;

import com.example.ticketShop.enums.EventType;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "events")
public class Event extends CreatedBase{

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Long capacity;

    @Column
    private OffsetDateTime dateTime;

    @Column
    private int counter = 0;

    @Column
    private int sum = 0;

    @Column
    private Long vacantSeats;

    @Column(columnDefinition = "varchar(15)", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EventType eventType;

    @ManyToOne
    @JoinColumn(name = "fk_event_user")
    private User organizer;

    @ManyToOne
    @JoinColumn(name = "fk_event_address")
    private Address address;
}
