package com.example.ticketShop.service;

import com.example.ticketShop.constants.ErrorCodes;
import com.example.ticketShop.dto.address.CreateAddressDto;
import com.example.ticketShop.dto.event.CreateEventDto;
import com.example.ticketShop.dto.event.UpdateEventDto;
import com.example.ticketShop.dto.ticketType.BuyTicketDto;
import com.example.ticketShop.dto.ticketType.CreateTicketTypeDto;
import com.example.ticketShop.entity.Address;
import com.example.ticketShop.entity.Event;
import com.example.ticketShop.exception.ConflictException;
import com.example.ticketShop.exception.NotFoundException;
import com.example.ticketShop.mapper.EventMapper;
import com.example.ticketShop.repository.EventRepository;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserService userService;
    private final AddressService addressService;
    private final TicketTypeService ticketTypeService;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public Event create(CreateEventDto createEvent) {
        if (eventRepository.existsByName(createEvent.getName())) {
            throw new ConflictException(ErrorCodes.EVENT_ALREADY_EXISTS);
        }

        Event event = eventMapper.mapToEntity(createEvent);
        event.setVacantSeats(event.getCapacity());
        event.setCounter(0);
        event.setSum(0);
        event.setOrganizer(userService.findByName(createEvent.getOrganizer(), ErrorCodes.USER_NOT_FOUND_BY_NAME));
        event.setDateTime(OffsetDateTime.now());
        event.setAddress(addressService.create(new CreateAddressDto(createEvent.getStreet(), createEvent.getCity(), createEvent.getNumber(), createEvent.getPostalCode(), createEvent.getCountry())));

        event = eventRepository.save(event);

        log.info("Successfully created {}.", event);
        return event;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Event findById(String uuid, String errorCode) {
        return eventRepository.findById(uuid).orElseThrow(() -> {
            log.info("Event with id '{}' does not exist.", uuid);
            throw new NotFoundException(errorCode);
        });
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Transactional
    public void updateById(String uuid, UpdateEventDto updateEvent) {
        Event existingEvent = findById(uuid, ErrorCodes.EVENT_NOT_FOUND_2);

        eventMapper.mapToEntity(updateEvent, existingEvent);

        existingEvent = eventRepository.save(existingEvent);

        log.info("Successfully updated {}.", existingEvent);
    }

    @Transactional
    public void deleteById(String uuid) {
        Event eventForDeletion = findById(uuid, ErrorCodes.EVENT_NOT_FOUND_3);

        eventRepository.deleteById(uuid);

        log.info("Successfully deleted {}.", eventForDeletion);
    }

    public void addTicketTypes(String uuid, List<CreateTicketTypeDto> ticketTypesDto) {
        Event event = findById(uuid, ErrorCodes.EVENT_NOT_FOUND_4);
        ticketTypeService.create(event, ticketTypesDto);
    }
}
