package com.hescha.cinemalibrary.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Role extends AbstractEntity{
    private String role;
    @Override
    public String toString() {
        return role;
    }
}
