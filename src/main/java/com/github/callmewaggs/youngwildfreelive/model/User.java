package com.github.callmewaggs.youngwildfreelive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class User {
    @Id
    String username;
    String password;
    String nickname;
    String email;
    String contact;
    LocalDateTime createdAt;

    public User() {

    }
}
