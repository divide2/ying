package com.mj.auth.res.controller;

import com.mj.auth.res.dto.OperAddDTO;
import com.mj.auth.res.service.OperService;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author bvvy
 */
@RestController
@Api("操作")
@RequestMapping("/v1/oper")
public class OperController {

    private final OperService operService;


    public OperController(OperService operService) {
        this.operService = operService;
    }

    @PostMapping
    public ResponseEntity<Messager> add(@Valid @RequestBody OperAddDTO operDTO, BindingResult br) {
        operService.add(operDTO);
        return Responser.created();
    }
}
