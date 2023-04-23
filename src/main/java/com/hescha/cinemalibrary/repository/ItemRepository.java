package com.hescha.cinemalibrary.repository;

import com.hescha.cinemalibrary.model.Item;
import com.hescha.cinemalibrary.model.ItemType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByName(String name);

    List<Item> findByNameContains(String name);

    List<Item> findByDescription(String description);

    List<Item> findByDescriptionContains(String description);

    Item findByType(ItemType type);

    Item findByDuration(Integer duration);

    Item findBySeriesCount(Integer seriesCount);

    Item findByReleaseYear(Integer releaseYear);

    List<Item> findByImage(String image);

    List<Item> findByImageContains(String image);

    List<Item> findByGenresContains(com.hescha.cinemalibrary.model.Genre genres);

    List<Item> findByCommentsContains(com.hescha.cinemalibrary.model.Comment comments);

    @Query("SELECT i FROM Item i LEFT JOIN i.comments c GROUP BY i ORDER BY COUNT(c) DESC, i.id ASC")
    List<Item> findTop3ItemsWithMostComments(Pageable pageable);
    @Query(value = "SELECT * FROM Item ORDER BY RAND() LIMIT 2", nativeQuery = true)
    List<Item> findTwoRandomItems();
}
