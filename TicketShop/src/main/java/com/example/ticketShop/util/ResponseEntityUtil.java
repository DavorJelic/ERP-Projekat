package com.example.ticketShop.util;


import com.example.ticketShop.dto.base.CreatedDtoBase;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ResponseEntityUtil {

    private ResponseEntityUtil() {
        super();
    }

    public static <T extends CreatedDtoBase> ResponseEntity<T> buildCreatedEntityResponse(String endpoint, T entity) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path(endpoint + "/{id}")
                .buildAndExpand(entity.getUuid()).toUri();

        return ResponseEntity.created(location).body(entity);
    }
}
