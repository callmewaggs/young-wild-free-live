package com.github.callmewaggs.youngwildfreelive.repository;

import com.github.callmewaggs.youngwildfreelive.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

  User findUserById(long id);

  Optional<User> findUserByUsernameAndPassword(String username, String password);

  Optional<User> findUserByUsername(String username);
}
