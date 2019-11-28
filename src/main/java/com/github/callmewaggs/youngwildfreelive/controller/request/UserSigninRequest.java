package com.github.callmewaggs.youngwildfreelive.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSigninRequest {
    String username;
    String password;
}
