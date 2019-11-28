package com.github.callmewaggs.youngwildfreelive.repository;

import com.github.callmewaggs.youngwildfreelive.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findUserByUsernameAndPassword(String username, String password);
    Optional<User> findUserByUsername(String username);
}
