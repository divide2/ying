package com.mj.auth.user.controller;

import com.mj.auth.user.model.User;
import com.mj.auth.user.service.UserService;
import com.mj.auth.user.to.JoinTO;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public OAuth2Authentication user(OAuth2Authentication o) {
        return o;
    }

    @PostMapping("/v1/join")
    public void join(@RequestBody JoinTO joinTO) {
        User user = new User();
        user.setUsername(joinTO.getAccount());
        user.setPassword(joinTO.getPassword());
        userService.save(user);
    }

}

