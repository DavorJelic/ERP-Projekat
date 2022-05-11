package com.example.ticketShop.dto.invoice;

import com.example.ticketShop.entity.User;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponseDto {

    private Long amount;

    private Integer numbOfItems;

    private OffsetDateTime dateTime;

    private User user;
}
