package com.example.ticketShop.dto.event;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private Long capacity;

    @NotEmpty
    private String organizer;

    @NotEmpty
    private String eventType;

    @NotEmpty
    private String street;

    @NotEmpty
    private String city;

    @NotEmpty
    private String number;

    @NotEmpty
    private String postalCode;

    @NotEmpty
    private String country;
}
