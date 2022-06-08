package com.example.ticketShop.controller;

import com.example.ticketShop.constants.ApiConstants;
import com.example.ticketShop.dto.invoice.InvoiceResponseDto;
import com.example.ticketShop.mapper.InvoiceMapper;
import com.example.ticketShop.service.InvoiceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_V1_INVOICE)
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceMapper invoiceMapper;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InvoiceResponseDto>> getAll() {
        return ResponseEntity.ok(invoiceMapper.mapToResponse(invoiceService.findAll()));
    }

    @GetMapping(value = "/{uuid}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InvoiceResponseDto>> getAllByUser(@RequestParam String uuid) {
        return ResponseEntity.ok(invoiceMapper.mapToResponse(invoiceService.findAllByUser(uuid)));
    }
}
