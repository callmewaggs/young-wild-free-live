package com.github.callmewaggs.youngwildfreelive.manager;

import com.github.callmewaggs.youngwildfreelive.model.User;
import com.github.callmewaggs.youngwildfreelive.repository.UserRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserManagerImpl implements UserManager {

  private UserRepository userRepository;

  public UserManagerImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void createUser(User user) {
    userRepository.save(user);
  }

  @Override
  public User findUserById(long id) {
    return userRepository.findUserById(id);
  }

  @Override
  public Optional<User> findUserByUsername(String username) {
    try {
      return userRepository.findUserByUsername(username);
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<User> findUserByUsernameAndPassword(String username, String password) {
    try {
      return userRepository.findUserByUsernameAndPassword(username, password);
    } catch (Exception e) {
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
