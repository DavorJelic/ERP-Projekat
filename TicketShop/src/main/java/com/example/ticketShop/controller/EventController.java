package com.example.ticketShop.controller;

import com.example.ticketShop.constants.ApiConstants;
import com.example.ticketShop.constants.ErrorCodes;
import com.example.ticketShop.dto.event.CreateEventDto;
import com.example.ticketShop.dto.event.EventResponseDto;
import com.example.ticketShop.dto.event.UpdateEventDto;
import com.example.ticketShop.dto.ticketType.BuyTicketDto;
import com.example.ticketShop.dto.ticketType.CreateTicketTypeDto;
import com.example.ticketShop.entity.Event;
import com.example.ticketShop.entity.TicketType;
import com.example.ticketShop.mapper.EventMapper;
import com.example.ticketShop.service.EventService;
import com.example.ticketShop.service.TicketTypeService;
import com.example.ticketShop.util.ResponseEntityUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_V1_EVENT)
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    //region CRUD
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EventResponseDto> create(@RequestBody final CreateEventDto createEvent) {
        final Event event = eventService.create(createEvent);

        return ResponseEntityUtil.buildCreatedEntityResponse(ApiConstants.API_V1_EVENT, eventMapper.mapToResponse(event));
    }

    @GetMapping(value = "/{uuid}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EventResponseDto> findById(@RequestParam String uuid) {
        final Event event = eventService.findById(uuid, ErrorCodes.EVENT_NOT_FOUND_1);

        return ResponseEntity.ok(eventMapper.mapToResponse(event));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventResponseDto>> findAll() {
        final List<Event> events = eventService.findAll();

        return ResponseEntity.ok(eventMapper.mapToResponse(events));
    }

    @PutMapping(value = "/{uuid}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateById(@PathVariable("uuid") final String uuid, @RequestBody final UpdateEventDto updateEvent) {
        eventService.updateById(uuid, updateEvent);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{uuid}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(@PathVariable("uuid") final String uuid) {
        eventService.deleteById(uuid);

        return ResponseEntity.noContent().build();
    }
    //endregion

    //region Actions
    @PostMapping(value = "/{uuid}/addTicketType", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTicketType(@PathVariable("uuid") final String uuid,
                                              @RequestBody List<CreateTicketTypeDto> ticketTypesDto) {
        eventService.addTicketTypes(uuid, ticketTypesDto);

        return ResponseEntity.noContent().build();
    }
    //endregion
}
