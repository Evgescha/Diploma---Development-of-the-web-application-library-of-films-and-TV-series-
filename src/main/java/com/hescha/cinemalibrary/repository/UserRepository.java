package com.hescha.cinemalibrary.repository;

import com.hescha.cinemalibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByUsernameContains(String username);

    List<User> findByPassword(String password);

    List<User> findByPasswordContains(String password);

    List<User> findByFirstname(String firstname);

    List<User> findByFirstnameContains(String firstname);

    List<User> findByLastname(String lastname);

    List<User> findByLastnameContains(String lastname);

    List<User> findByEmail(String email);

    List<User> findByEmailContains(String email);

    List<User> findByImage(String image);

    List<User> findByImageContains(String image);

    Set<User> findByFavouritesItemsContains(com.hescha.cinemalibrary.model.Item favouritesItems);

    Set<User> findByFeatureItemsContains(com.hescha.cinemalibrary.model.Item featureItems);

    Set<User> findByInprogresItemsContains(com.hescha.cinemalibrary.model.Item inprogresItems);

    Set<User> findByWatchedItemsContains(com.hescha.cinemalibrary.model.Item watchedItems);

    Set<User> findByRolesContains(com.hescha.cinemalibrary.model.Role roles);
}
