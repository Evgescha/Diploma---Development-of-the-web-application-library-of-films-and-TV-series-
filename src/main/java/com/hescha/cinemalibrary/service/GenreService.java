package com.hescha.cinemalibrary.service;

import com.hescha.cinemalibrary.model.Genre;
import com.hescha.cinemalibrary.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GenreService extends CrudService<Genre> {

    private final GenreRepository repository;

    public GenreService(GenreRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Genre> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Genre> findByNameContains(String name) {
        return repository.findByNameContains(name);
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
}
