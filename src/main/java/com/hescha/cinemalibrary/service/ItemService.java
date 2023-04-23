package com.hescha.cinemalibrary.service;

import com.hescha.cinemalibrary.model.Genre;
import com.hescha.cinemalibrary.model.Item;
import com.hescha.cinemalibrary.model.ItemType;
import com.hescha.cinemalibrary.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ItemService extends CrudService<Item> {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Item update(Long id, Item entity) {
        Item read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Item not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(Item entity, Item read) {
        read.setName(entity.getName());
        read.setDescription(entity.getDescription());
        read.setType(entity.getType());
        read.setDuration(entity.getDuration());
        read.setSeriesCount(entity.getSeriesCount());
        read.setReleaseYear(entity.getReleaseYear());
        read.setImage(entity.getImage());
        read.setGenres(entity.getGenres());
    }

    public List<Item> readTop3Commented() {
        Pageable topThree = PageRequest.of(0, 4);
        return repository.findTop3ItemsWithMostComments(topThree);
    }

    public Page<Item> readPage(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber-1, 4);
        return repository.findAll(page);
    }

    public List<Item> findTwoRandomItems(){
        return repository.findTwoRandomItems();
    }

    public List<Item> findByNameContainsOrDescriptionContains(String searchPhrase) {
        return repository.findAllByNameOrDescriptionIgnoreCase(searchPhrase);
    }

    public List<Item> findItemsBySearchPhraseAndGenreId(String searchPhrase, Integer genreId){
        return repository.findItemsBySearchPhraseAndGenreId(searchPhrase, Long.valueOf(genreId));
    }

    public List<Item> findItemsBySearchPhrase(String searchPhrase){
        return repository.findItemsBySearchPhrase(searchPhrase);
    }
}
