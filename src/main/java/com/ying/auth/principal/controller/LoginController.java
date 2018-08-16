package com.ying.auth.principal.controller;

import com.ying.auth.principal.model.User;
import com.ying.auth.principal.model.UserDetailsImpl;
import com.ying.auth.principal.service.UserService;
import com.ying.auth.principal.dto.JoinDTO;
import io.swagger.annotations.Api;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author bvvy
 *
 * 用户controller
 *
 */
@RestController
@Api(tags = "注册/获取用户信息")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public Object user(OAuth2Authentication o) {
        UserDetailsImpl principal = (UserDetailsImpl) o.getPrincipal();
        principal.setPassword(null);
        return principal;
    }

    @PostMapping("/v1/join")
    public void join(@Valid  @RequestBody JoinDTO joinTO, BindingResult br) {
        User user = new User();
        user.setUsername(joinTO.getAccount());
        user.setPassword(joinTO.getPassword());
        userService.add(user);
    }

}

