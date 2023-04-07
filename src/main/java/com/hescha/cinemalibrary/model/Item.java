package com.hescha.cinemalibrary.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Item extends AbstractEntity{
    private String name;
    private String description;
    private ItemType type;
    private Integer duration;
    private Integer seriesCount;
    private Integer releaseYear;
    private String image;
    @OneToMany
    private List<Genre> genres = new ArrayList<>();
    @OneToMany
    private List<Comment> comments = new ArrayList<>();

}
