package com.hescha.cinemalibrary.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Item extends AbstractEntity{
    private String name;
    @Column(length = 4096)
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

    @OneToMany(mappedBy = "item", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Comment> comments = new ArrayList<>();

}
