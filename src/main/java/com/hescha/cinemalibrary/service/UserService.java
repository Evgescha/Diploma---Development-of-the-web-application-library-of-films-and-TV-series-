package com.hescha.cinemalibrary.service;

import com.hescha.cinemalibrary.model.Item;
import com.hescha.cinemalibrary.model.User;
import com.hescha.cinemalibrary.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UserService extends CrudService<User>
        implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository repository;
    private final RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository,
                       RoleService roleService) {
        super(repository);
        this.repository = repository;
        this.roleService = roleService;
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User update(Long id, User entity) {
        User read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity User not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(User entity, User read) {
        read.setUsername(entity.getUsername());
        read.setPassword(entity.getPassword());
        read.setFirstname(entity.getFirstname());
        read.setLastname(entity.getLastname());
        read.setEmail(entity.getEmail());
        read.setImage(entity.getImage());
    }

    public boolean registerNew(User entity) {
        entity.getRoles().add(roleService.read(1));
        if (repository.findByUsername(entity.getUsername()) != null) {
            return false;
        }
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        try {
            create(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), List.of());
    }

    @Transactional
    public void removeItemFromUserLists(Item item) {
        List<User> users = repository.findAll();
        for (User user : users) {
            user.getFavouritesItems().remove(item);
            user.getFeatureItems().remove(item);
            user.getInprogresItems().remove(item);
            user.getWatchedItems().remove(item);
            repository.save(user);
        }
    }
}
