package com.example.ticketShop.dto.ticketType;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketTypeDto {

    @NotEmpty
    private String typeName;

    @NotEmpty
    private Long price;

    @NotEmpty
    private int numberOfCards;
}
