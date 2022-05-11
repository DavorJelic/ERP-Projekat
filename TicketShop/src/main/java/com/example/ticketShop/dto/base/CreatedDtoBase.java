package com.example.ticketShop.dto.base;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class CreatedDtoBase {

    private String uuid;

    private OffsetDateTime createdAt;
}
