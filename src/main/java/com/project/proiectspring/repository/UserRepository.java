package com.project.proiectspring.repository;


import com.project.proiectspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(String lastName, String firstName);

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findById(long id);

}
