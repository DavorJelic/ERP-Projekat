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
@Table(name = "events_participants")
public class EventParticipant extends CreatedBase{

    @ManyToOne
    @JoinColumn(name = "fk_ep_event")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "fk_ep_participant")
    private Participant participant;

    @Column
    private String role;
}
