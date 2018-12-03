package com.ying.auth.controller;

import com.ying.auth.dto.OperAddDTO;
import com.ying.auth.service.OperService;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
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
@Api(tags = "操作",hidden = true)
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
