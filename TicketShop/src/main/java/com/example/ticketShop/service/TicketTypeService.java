package com.example.ticketShop.service;

import com.example.ticketShop.constants.ErrorCodes;
import com.example.ticketShop.dto.ticketType.CreateTicketTypeDto;
import com.example.ticketShop.entity.Event;
import com.example.ticketShop.entity.TicketType;
import com.example.ticketShop.exception.ConflictException;
import com.example.ticketShop.exception.NotFoundException;
import com.example.ticketShop.mapper.TicketTypeMapper;
import com.example.ticketShop.repository.TicketTypeRepository;
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
public class TicketTypeService {

    private final TicketTypeRepository ticketTypeRepository;
    private final TicketTypeMapper ticketTypeMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void create(Event event, List<CreateTicketTypeDto> ticketTypesDto) {
        List<TicketType> ticketTypes = ticketTypeMapper.mapToEntity(ticketTypesDto);
        ticketTypes.forEach(ticketType -> ticketType.setEvent(event));

        ticketTypeRepository.saveAll(ticketTypes);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public TicketType findById(String uuid, String errorCode) {
        return ticketTypeRepository.findById(uuid).orElseThrow(() -> {
            log.info("Ticket type with id '{}' does not exist.", uuid);
            throw new NotFoundException(errorCode);
        });
    }
}
