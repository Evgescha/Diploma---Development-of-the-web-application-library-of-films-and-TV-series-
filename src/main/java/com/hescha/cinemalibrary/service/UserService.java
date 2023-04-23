package com.hescha.cinemalibrary.service;

import com.hescha.cinemalibrary.model.User;
import com.hescha.cinemalibrary.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public List<User> findByUsernameContains(String username) {
        return repository.findByUsernameContains(username);
    }

    public List<User> findByPassword(String password) {
        return repository.findByPassword(password);
    }

    public List<User> findByPasswordContains(String password) {
        return repository.findByPasswordContains(password);
    }

    public List<User> findByFirstname(String firstname) {
        return repository.findByFirstname(firstname);
    }

    public List<User> findByFirstnameContains(String firstname) {
        return repository.findByFirstnameContains(firstname);
    }

    public List<User> findByLastname(String lastname) {
        return repository.findByLastname(lastname);
    }

    public List<User> findByLastnameContains(String lastname) {
        return repository.findByLastnameContains(lastname);
    }

    public List<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<User> findByEmailContains(String email) {
        return repository.findByEmailContains(email);
    }

    public List<User> findByImage(String image) {
        return repository.findByImage(image);
    }

    public List<User> findByImageContains(String image) {
        return repository.findByImageContains(image);
    }

    public List<User> findByAddress(String address) {
        return repository.findByAddress(address);
    }

    public List<User> findByAddressContains(String address) {
        return repository.findByAddressContains(address);
    }

    public Set<User> findByFavouritesItemsContains(com.hescha.cinemalibrary.model.Item favouritesItems) {
        return repository.findByFavouritesItemsContains(favouritesItems);
    }

    public Set<User> findByFeatureItemsContains(com.hescha.cinemalibrary.model.Item featureItems) {
        return repository.findByFeatureItemsContains(featureItems);
    }

    public Set<User> findByInprogresItemsContains(com.hescha.cinemalibrary.model.Item inprogresItems) {
        return repository.findByInprogresItemsContains(inprogresItems);
    }

    public Set<User> findByWatchedItemsContains(com.hescha.cinemalibrary.model.Item watchedItems) {
        return repository.findByWatchedItemsContains(watchedItems);
    }

    public Set<User> findByRolesContains(com.hescha.cinemalibrary.model.Role roles) {
        return repository.findByRolesContains(roles);
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
        read.setAddress(entity.getAddress());
        read.setFavouritesItems(entity.getFavouritesItems());
        read.setFeatureItems(entity.getFeatureItems());
        read.setInprogresItems(entity.getInprogresItems());
        read.setWatchedItems(entity.getWatchedItems());
        read.setRoles(entity.getRoles());
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
}
