package com.example.ticketShop.dto.event;

import com.example.ticketShop.dto.base.CreatedDtoBase;
import com.example.ticketShop.entity.Address;
import com.example.ticketShop.entity.User;
import com.example.ticketShop.enums.EventType;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDto extends CreatedDtoBase {

    private String name;

    private String description;

    private OffsetDateTime dateTime;

    private int stars;

    private Long vacantSeats;

    private User organizer;

    private Address address;

    private EventType eventType;
}
