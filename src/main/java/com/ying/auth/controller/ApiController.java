package com.ying.auth.controller;

import com.ying.auth.service.ApiService;
import com.ying.auth.vo.ApiVO;
import com.ying.core.er.Responser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author bvvy
 */
@RestController
@RequestMapping("/v1/api")
@Api(tags = "Api")
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/all")
    @ApiOperation("全部的api")
    public ResponseEntity<List<ApiVO>> all() {
        return Responser.ok(apiService.findAll().stream().map(api ->
                        ApiVO.builder()
                                .id(api.getId())
                                .name(api.getName())
                                .method(api.getMethod())
                                .path(api.getPath())
                                .build())
                        .collect(Collectors.toList()));
    }
}
