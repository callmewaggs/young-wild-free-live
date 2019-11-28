package com.github.callmewaggs.youngwildfreelive.service;

import com.github.callmewaggs.youngwildfreelive.model.User;
import com.github.callmewaggs.youngwildfreelive.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        try {
            return userRepository.findUserByUsername(username);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findMemberByUsernameAndPassword(String username, String password) {
        try {
            return userRepository.findUserByUsernameAndPassword(username, password);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUserByUsername(String username) {

    }
}
