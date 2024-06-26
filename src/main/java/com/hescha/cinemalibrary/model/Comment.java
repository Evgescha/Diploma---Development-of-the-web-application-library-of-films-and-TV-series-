package com.hescha.cinemalibrary.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
public class Comment extends AbstractEntity{
    @ManyToOne
    private User owner;
    @ManyToOne
    private Item item;

    private LocalDateTime created = LocalDateTime.now();

    @Column(length = 4096)
    private String message;

    @Transient
    public String getFormattedCreated() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");
        return created.format(formatter);
    }

    @Override
    public String
    toString() {
        return "Comment{" +
                "owner=" + owner.getUsername() +
                ", item=" + item.id +
                ", created=" + created +
                ", message='" + message + '\'' +
                '}';
    }
}