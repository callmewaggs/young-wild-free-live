package com.github.callmewaggs.youngwildfreelive.model;

import com.github.callmewaggs.youngwildfreelive.controller.vo.UserVO;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String username;
  String password;
  String nickname;
  String email;
  String contact;
  LocalDateTime createdAt;

  public User() {

  }

  public User(UserVO userVO, LocalDateTime createdAt) {
    this.username = userVO.getUsername();
    this.password = userVO.getPassword();
    this.nickname = userVO.getNickname();
    this.email = userVO.getEmail();
    this.contact = userVO.getContact();
    this.createdAt = createdAt;
  }
}
