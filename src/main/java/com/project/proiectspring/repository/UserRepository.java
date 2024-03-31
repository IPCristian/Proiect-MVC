package com.project.proiectspring.repository;


import com.project.proiectspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(String lastName, String firstName);

}
