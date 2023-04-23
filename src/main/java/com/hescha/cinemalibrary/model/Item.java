package com.hescha.cinemalibrary.model;

import lombok.Data;

import javax.persistence.*;
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
    @ManyToMany
    @JoinTable(
            name = "Item_Genres",
            joinColumns = @JoinColumn(name = "ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name = "GENRES_ID")
    )
    private List<Genre> genres = new ArrayList<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

}
