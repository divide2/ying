package com.ying.auth.principal.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小程序登录
 * @author bvvy
 * @date 2018/8/28
 */
@RequestMapping("/v1/mp")
@RestController
public class MpLoginController {

    @PostMapping("/login")
    public void login(@RequestBody MpLoginDTO mpLogin) {
    }

    @Data
    private static class MpLoginDTO {

        private String code;
    }
}
