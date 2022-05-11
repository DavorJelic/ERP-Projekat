package com.example.ticketShop.repository;

import com.example.ticketShop.entity.Invoice;
import com.example.ticketShop.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    List<Invoice> findAllByUser(User user);
}
