package com.example.ticketShop.dto.ticketType;

import com.example.ticketShop.entity.Event;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
