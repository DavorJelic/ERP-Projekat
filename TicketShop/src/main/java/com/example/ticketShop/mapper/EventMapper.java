package com.example.ticketShop.mapper;

import com.example.ticketShop.dto.event.CreateEventDto;
import com.example.ticketShop.dto.event.UpdateEventDto;
import com.example.ticketShop.entity.Event;
import com.example.ticketShop.dto.event.EventResponseDto;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public abstract class EventMapper {

    @Named("mapToResponse")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "uuid")
    @Mapping(target = "name")
    @Mapping(target = "description")
    @Mapping(target = "dateTime")
    //@Mapping(target = "stars", expression = "java(event.getSum()/event.getCounter())")
    @Mapping(target = "vacantSeats")
    @Mapping(target = "organizer")
    @Mapping(target = "address")
    @Mapping(target = "eventType")
    public abstract EventResponseDto mapToResponse(Event event);

    @IterableMapping(qualifiedByName = "mapToResponse")
    public abstract List<EventResponseDto> mapToResponse(List<Event> events);

    @Named("mapToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name")
    @Mapping(target = "description")
    @Mapping(target = "capacity")
    @Mapping(target = "eventType")
    public abstract Event mapToEntity(CreateEventDto createEvent);

    @Named("mapToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name")
    @Mapping(target = "description")
    @Mapping(target = "capacity")
    @Mapping(target = "eventType")
    @Mapping(target = "organizer")
    @Mapping(target = "address")
    public void mapToEntity(UpdateEventDto updateEvent, @MappingTarget Event event) {
    }
}
