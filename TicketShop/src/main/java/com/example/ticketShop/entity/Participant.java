package com.example.ticketShop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "participants")
public class Participant extends CreatedBase{

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private String profession;
}
