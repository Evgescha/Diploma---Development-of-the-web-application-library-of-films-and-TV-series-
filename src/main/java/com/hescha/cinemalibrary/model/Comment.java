package com.hescha.cinemalibrary.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment extends AbstractEntity{
    @ManyToOne
    private User owner;
    private LocalDateTime created;
    private String message;
}