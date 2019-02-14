package com.ying.client.controller;

import com.ying.client.dto.UserInfoVO;
import com.ying.client.service.ClientService;
import com.ying.core.er.Responser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bvvy
 * @date 2019/2/14
 */
@RestController
@RequestMapping("/v2")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserInfoVO> getUserInfo(@PathVariable Integer id) {
        UserInfoVO userInfoVO = clientService.getUserInfo(id);
        return Responser.ok(userInfoVO);
    }

}
