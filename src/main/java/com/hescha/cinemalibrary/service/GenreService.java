package com.hescha.cinemalibrary.service;

import com.hescha.cinemalibrary.model.Genre;
import com.hescha.cinemalibrary.model.Item;
import com.hescha.cinemalibrary.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GenreService extends CrudService<Genre> {

    private final GenreRepository repository;
    @Autowired
    private ItemRepository itemRepository;


    public GenreService(GenreRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Genre update(Long id, Genre entity) {
        Genre read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Genre not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(Genre entity, Genre read) {
        read.setName(entity.getName());
    }

    @Override
    public void delete(long id) {
        // Найти жанр по идентификатору
        Genre genre = repository.findById(id).orElse(null);

        if (genre != null) {
            // Найти все элементы, связанные с жанром
            List<Item> itemsWithGenre = itemRepository.findByGenresContains(genre);

            // Удалить жанр из списка жанров каждого элемента
            for (Item item : itemsWithGenre) {
                item.getGenres().remove(genre);
                itemRepository.save(item);
            }

            // Удалить жанр из базы данных
            repository.deleteById(id);
        }
    }
}
