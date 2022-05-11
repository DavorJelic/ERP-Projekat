package com.example.ticketShop.entity;

import com.example.ticketShop.enums.UserType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "users")
public class User extends CreatedBase{

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column(columnDefinition = "varchar(15) default 'Basic_user'", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserType userType = UserType.BASIC_USER;

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name = "fk_user_address")
    private Address address;
}
