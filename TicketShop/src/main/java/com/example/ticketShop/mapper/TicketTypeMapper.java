package com.example.ticketShop.mapper;

import com.example.ticketShop.dto.event.EventResponseDto;
import com.example.ticketShop.dto.ticketType.CreateTicketTypeDto;
import com.example.ticketShop.entity.Event;
import com.example.ticketShop.entity.TicketType;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public abstract class TicketTypeMapper {

    @Named("mapToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "typeName")
    @Mapping(target = "price")
    @Mapping(target = "numberOfCards")
    public abstract TicketType mapToEntity(CreateTicketTypeDto ticketTypeDto);

    @IterableMapping(qualifiedByName = "mapToEntity")
    public abstract List<TicketType> mapToEntity(List<CreateTicketTypeDto> ticketTypes);
}
