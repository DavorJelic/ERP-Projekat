package com.example.ticketShop.service;

import com.example.ticketShop.constants.ErrorCodes;
import com.example.ticketShop.entity.Invoice;
import com.example.ticketShop.entity.User;
import com.example.ticketShop.exception.NotFoundException;
import com.example.ticketShop.repository.InvoiceRepository;
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
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final UserService userService;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public Invoice create(User user) {
        Invoice invoice = new Invoice();

        invoice.setUser(user);
        invoice.setNumbOfItems(0);
        invoice.setAmount(0L);

        invoice = invoiceRepository.save(invoice);

        return invoice;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Invoice findById(String uuid) {
        return invoiceRepository.findById(uuid).orElseThrow(()->{
            log.info("Event with id '{}' does not exist.", uuid);
            throw new NotFoundException(ErrorCodes.INVOICE_NOT_FOUND);
        });
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Invoice> findAllByUser(String uuid) {
        User user = userService.findById(uuid, ErrorCodes.USER_NOT_FOUND_BY_ID_1);

        return invoiceRepository.findAllByUser(user);
    }
}
