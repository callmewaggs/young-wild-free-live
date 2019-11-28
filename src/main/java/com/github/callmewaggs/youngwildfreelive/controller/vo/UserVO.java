package com.github.callmewaggs.youngwildfreelive.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    String username;
    String password;
    String nickname;
    String email;
    String contact;
}
