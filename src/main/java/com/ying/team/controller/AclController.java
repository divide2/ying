package com.ying.team.controller;

import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.team.dto.AclDTO;
import com.ying.team.service.AclService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author bvvy
 * @date 2019/2/26
 */
@RestController
@Api(tags = "权限")
@RequestMapping("/v1/auth")
public class AclController {
    private final AclService aclService;

    public AclController(AclService aclService) {
        this.aclService = aclService;
    }

    @PostMapping
    @ApiOperation("设置权限")
    public ResponseEntity<Messager> add(@RequestBody @Valid AclDTO dto, Errors errors) {
        aclService.add(dto);
        return Responser.created();
    }

}
