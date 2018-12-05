package com.ying.auth.controller;

import com.ying.auth.dto.JoinDTO;
import com.ying.auth.model.User;
import com.ying.auth.model.UserDetailsImpl;
import com.ying.auth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.AbstractEndpoint;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author bvvy
 * <p>
 * 用户controller
 */
@Api(tags = "注册/获取用户信息")
@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/v1/user")
    @ApiOperation("获取用户信息")
    public Object user(OAuth2Authentication o) {
        return o;
    }

    @PostMapping("join")
    @ApiOperation("注册")
    public void join(@Valid @RequestBody JoinDTO joinTO, BindingResult br) {
        User user = new User();
        user.setUsername(joinTO.getAccount());
        user.setPassword(joinTO.getPassword());
        userService.add(user);
    }


}

