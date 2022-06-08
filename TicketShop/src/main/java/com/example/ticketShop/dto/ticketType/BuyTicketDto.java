package com.example.ticketShop.dto.ticketType;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyTicketDto {

    @NotEmpty
    private int number;

    @NotEmpty
    private String ticketType;

    @NotEmpty
    private String event;
}
