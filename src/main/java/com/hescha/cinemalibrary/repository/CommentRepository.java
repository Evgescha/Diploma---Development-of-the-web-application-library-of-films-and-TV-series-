package com.hescha.cinemalibrary.repository;

import com.hescha.cinemalibrary.model.Comment;
import com.hescha.cinemalibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByOwner(User owner);

    Comment findByCreated(LocalDateTime created);

    List<Comment> findByMessage(String message);

    List<Comment> findByMessageContains(String message);
}
