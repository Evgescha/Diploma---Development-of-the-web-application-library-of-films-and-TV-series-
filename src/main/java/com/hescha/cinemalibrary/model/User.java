package com.hescha.cinemalibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "myUser")
@Entity
public class User extends AbstractEntity {
    private String username;
    @JsonIgnore
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String image = "/img/user.png";
    @ManyToMany
    private Set<Item> favouritesItems = new HashSet();
    @ManyToMany
    private Set<Item> featureItems = new HashSet();
    @ManyToMany
    private Set<Item> inprogresItems = new HashSet();
    @ManyToMany
    private Set<Item> watchedItems = new HashSet<>();
    @ManyToMany
    private Set<Role> roles = new HashSet();

    @Override
    public String toString() {
        return username;
    }
}
