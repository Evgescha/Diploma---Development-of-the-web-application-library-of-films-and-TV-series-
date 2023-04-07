package com.hescha.cinemalibrary.service;

import com.hescha.cinemalibrary.model.Comment;
import com.hescha.cinemalibrary.model.User;
import com.hescha.cinemalibrary.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class CommentService extends CrudService<Comment> {

    private final CommentRepository repository;

    public CommentService(CommentRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Comment findByOwner(User owner) {
        return repository.findByOwner(owner);
    }

    public Comment findByCreated(LocalDateTime created) {
        return repository.findByCreated(created);
    }

    public List<Comment> findByMessage(String message) {
        return repository.findByMessage(message);
    }

    public List<Comment> findByMessageContains(String message) {
        return repository.findByMessageContains(message);
    }


    public Comment update(Long id, Comment entity) {
        Comment read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Comment not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(Comment entity, Comment read) {
        read.setOwner(entity.getOwner());
        read.setCreated(entity.getCreated());
        read.setMessage(entity.getMessage());
    }
}
