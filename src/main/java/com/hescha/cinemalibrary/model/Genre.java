package com.hescha.cinemalibrary.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Genre extends AbstractEntity{
    private String name;
}
