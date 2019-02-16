package com.ying.auth.controller;

import com.ying.auth.dto.JoinDTO;
import com.ying.auth.model.User;
import com.ying.auth.service.UserService;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    private final ConsumerTokenServices consumerTokenServices;
    @Value("${divide.defaults.avatar}")
    private String defaultAvatar;

    public LoginController(UserService userService,
                           ConsumerTokenServices consumerTokenServices) {
        this.userService = userService;
        this.consumerTokenServices = consumerTokenServices;
    }

    @GetMapping("/v1/user")
    @ApiOperation("获取用户信息")
    public Object user(OAuth2Authentication o) {
        return o;
    }

    @PostMapping("/join")
    @ApiOperation("注册")
    public ResponseEntity<Messager> join(@Valid @RequestBody JoinDTO joinTO, BindingResult br) {
        User user = new User();
        user.setUsername(joinTO.getAccount());
        user.setPassword(joinTO.getPassword());
        user.setAvatar(defaultAvatar);
        user.setNickname(joinTO.getAccount());
        user.setPhone(joinTO.getAccount());
        user.setEnabled(true);
        userService.add(user);
        return Responser.created();
    }

    @PostMapping("/v1/logout")
    @ApiOperation("退出登录")
    public ResponseEntity<Messager> logout(@RequestHeader("Authorization") String authorization) {
        String tokenValue = authorization.replace("Bearer", "").trim();
        consumerTokenServices.revokeToken(tokenValue);
        return Responser.deleted();
    }
}

