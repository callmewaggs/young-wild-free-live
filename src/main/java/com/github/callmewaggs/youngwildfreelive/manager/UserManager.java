package com.github.callmewaggs.youngwildfreelive.manager;

import com.github.callmewaggs.youngwildfreelive.model.User;
import java.util.Optional;

public interface UserManager {

    void createUser(User user);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByUsernameAndPassword(String username, String password);

    void updateUser(User user);

    void deleteUserByUsername(String username);
}
