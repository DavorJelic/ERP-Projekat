package com.example.ticketShop.entity;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "comments")
public class Comment extends CreatedBase{

    @Column(nullable = false)
    private String content;

    @Column
    private OffsetDateTime commentedAt;

    @ManyToOne
    @JoinColumn(name = "fk_comment_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_comment_event")
    private Event event;
}
