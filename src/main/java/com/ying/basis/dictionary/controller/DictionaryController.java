package com.ying.basis.dictionary.controller;

import com.ying.core.er.Responser;
import com.ying.basis.dictionary.model.Dictionary;
import com.ying.basis.dictionary.service.DictionaryService;
import com.ying.basis.dictionary.vo.DictionaryVO;
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
 * @date 2018/8/10
 */
@RestController
@RequestMapping("/v1/dictionary")
@Api(tags = "字典")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/all")
    @ApiOperation("查询所有数据，前端处理 做filter")
    public ResponseEntity<List<DictionaryVO>> all() {
        List<Dictionary> dictionaries = dictionaryService.findAll();
        List<DictionaryVO> vos = dictionaries.stream().map(DictionaryVO::of).collect(Collectors.toList());
        return Responser.ok(vos);
    }


}
