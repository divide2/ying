package com.divide2.basis.controller;

import com.divide2.basis.model.Tag;
import com.divide2.basis.service.TagService;
import com.divide2.basis.vo.TagVO;
import com.divide2.core.er.Responser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2018/7/19
 */

@RestController
@Api("标签")
@RequestMapping("/v1/tag")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/all")
    @ApiOperation("全部标签")
    public ResponseEntity<List<TagVO>> all() {
        List<Tag> all = tagService.findAll();
        List<TagVO> vos = all.stream().map(TagVO::of).collect(Collectors.toList());
        return Responser.ok(vos);
    }
    @GetMapping("/count/{count}")
    @ApiOperation("获取一定数量的标签")
    public ResponseEntity<List<TagVO>> findByCount(@PathVariable Integer count) {
        return Responser.ok(tagService.findByCount(count));
    }

}
