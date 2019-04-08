package com.ying.auth.controller;

import com.ying.auth.dto.JoinDTO;
import com.ying.auth.model.User;
import com.ying.auth.service.UserService;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.core.er.VerificationCodeManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;


/**
 * @author bvvy
 * <p>
 * 用户controller
 */
@Api(tags = "登录注册")
@RestController
public class LoginController {

    private final UserService userService;
    private final ConsumerTokenServices consumerTokenServices;
    @Value("${divide.defaults.avatar}")
    private String defaultAvatar;
    private final VerificationCodeManager verificationCodeManager;


    public LoginController(UserService userService,
                           ConsumerTokenServices consumerTokenServices,
                           VerificationCodeManager verificationCodeManager) {
        this.userService = userService;
        this.consumerTokenServices = consumerTokenServices;
        this.verificationCodeManager = verificationCodeManager;
    }

    @GetMapping("/v1/user")
    @ApiOperation("获取用户信息")
    public Object user(OAuth2Authentication o) {
        return o;
    }

    @PostMapping("/join")
    @ApiOperation("注册")
    public ResponseEntity<Messager> join(@Valid @RequestBody JoinDTO join, BindingResult br) {
        if (!verificationCodeManager.validate(join.getPhoneNumber(), join.getVerifyCode())) {
            throw new ValidationException("wrong_verify_code");
        }
        User user = new User();
        user.setPassword(join.getPassword());
        user.setAvatar(defaultAvatar);
        user.setNickname(join.getNickname());
        user.setPhone(join.getPhoneNumber());
        user.setEnabled(true);
        userService.add(user);
        verificationCodeManager.remove(join.getPhoneNumber());
        return Responser.created();
    }

    @PostMapping("/v1/logout")
    @ApiOperation("退出登录")
    public ResponseEntity<Messager> logout(@RequestHeader("Authorization") String authorization) {
        String tokenValue = authorization.replace("Bearer", "").trim();
        consumerTokenServices.revokeToken(tokenValue);
        return Responser.deleted();
    }
    @ApiOperation("获取验证码")
    @PostMapping("/v1/verify/code")
    public ResponseEntity<Messager> sendVerifyCode(@RequestBody Phone phone) {
        verificationCodeManager.sendSms(phone.getPhoneNumber());
        return Responser.created();
    }

    @Data
    private static class Phone {
        private String phoneNumber;
    }

}

