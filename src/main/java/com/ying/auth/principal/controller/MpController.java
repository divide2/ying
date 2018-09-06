package com.ying.auth.principal.controller;

import com.ying.auth.principal.model.User;
import com.ying.auth.principal.service.UserService;
import com.ying.auth.principal.vo.MpUserVO;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Jsoner;
import com.ying.core.er.Responser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bvvy
 * @date 2018/8/28
 */
@RestController
@Api(tags = "微信小程序接口")
class MpController {

    private static final String URL = "https://api.weixin.qq.com/sns/jscode2session";
    private static final String APPID = "wx3fdfbf00e5a3b9f9";
    private static final String SECRET = "4d17a936b70d0786f59d1fa972c92c49";

    private final UserService userService;

    private static Map<String, String> SKEY_KEEPER = new ConcurrentHashMap<>();
    MpController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login/mp")
    @ApiOperation("小程序登录")
    public ResponseEntity<Skey> login(@RequestBody @Valid MpCode code, BindingResult br) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(URL+"?appid="+APPID+"&secret="+SECRET+"&js_code="+code.getCode()+"&grant_type=authorization_code", String.class);
        MpLoginReturn open = Jsoner.fromSnake(result, MpLoginReturn.class);
        SKEY_KEEPER.put(open.getSessionKey(), open.getOpenid());
        return Responser.ok(new Skey(open.getSessionKey()));
    }

    @PostMapping("/v1/mp/user")
    @ApiOperation("保存用户")
    public ResponseEntity<Messager> saveMpUser(MpUserVO mpUser) {
//        User user = userService.getByUsername("123");
        String openId = SKEY_KEEPER.get(mpUser.getSkey());
        User user = userService.getByUsername(openId);
        if (user == null) {
            user = new User();
        }
        user.setUsername(openId);
        user.setNickname(mpUser.getNickName());
        user.setAvatar(mpUser.getAvatarUrl());
        user.setPassword(openId);
        user.setGender(mpUser.getGender());
        user.setEnabled(true);
        userService.update(user);
        this.login(user.getUsername(), user.getPassword());
        return Responser.created();

    }

    private void login(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>(4);
        params.put("username", username);
        params.put("password", password);
        params.put("scope", "webclient");
        params.put("grant_type", "password");
        HttpEntity<Map<String, String>> form = new HttpEntity<>(params);
        ResponseEntity<String> resp = restTemplate.postForEntity("/oauth/token", form, String.class);
        System.out.println(resp.getBody());
    }



}

@Data
class MpCode {
    @NotEmpty
    private String code;
}

@Data
class MpLoginReturn {
    private String openid;
    private String sessionKey;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Skey {
    private String skey;
}