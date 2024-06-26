package com.hescha.cinemalibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "myUser")
@Entity
public class User extends AbstractEntity {
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String image = "/img/user.png";
    @ManyToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Item> favouritesItems = new HashSet();

    @ManyToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Item> featureItems = new HashSet();

    @ManyToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Item> inprogresItems = new HashSet();

    @ManyToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Item> watchedItems = new HashSet<>();
    @ManyToMany
    private Set<Role> roles = new HashSet();

    @Override
    public String toString() {
        return username;
    }
}
