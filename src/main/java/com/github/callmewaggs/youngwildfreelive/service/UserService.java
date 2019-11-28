package com.github.callmewaggs.youngwildfreelive.service;

import com.github.callmewaggs.youngwildfreelive.model.User;

import java.util.Optional;

public interface UserService {
    void createUser(User user);

    Optional<User> findUserByUsername(String username);

    Optional<User> findMemberByUsernameAndPassword(String username, String password);

    void updateUser(User user);

    void deleteUserByUsername(String username);
}
