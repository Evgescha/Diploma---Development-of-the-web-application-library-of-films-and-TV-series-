package com.hescha.cinemalibrary.repository;

import com.hescha.cinemalibrary.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByName(String name);

    List<Genre> findByNameContains(String name);
}
