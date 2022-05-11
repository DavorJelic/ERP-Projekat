package com.example.ticketShop.entity;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoices")
public class Invoice extends CreatedBase{

    @Column
    private Long amount;

    @Column
    private Integer numbOfItems;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(columnDefinition = "timestamp with time zone default now()", updatable = false, nullable = false)
    private OffsetDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "fk_invoice_user")
    private User user;
}
