package com.mj.auth.res.controller;

import com.mj.auth.res.service.ApiService;
import com.mj.auth.res.vo.ApiVO;
import com.mj.core.er.Responser;
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
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/all")
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
