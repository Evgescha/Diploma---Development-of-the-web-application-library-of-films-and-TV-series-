package com.hescha.cinemalibrary.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Role extends AbstractEntity{
    @Column(unique = true)
    private String role;
    @Override
    public String toString() {
        return role;
    }
}
