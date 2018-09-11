package com.ying.basis.star.controller;

import com.ying.basis.star.dto.StarAddDTO;
import com.ying.basis.star.model.Star;
import com.ying.basis.star.service.StarService;
import com.ying.basis.star.vo.StarVO;
import com.ying.core.data.del.SingleId;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author bvvy
 * @date 2018/9/10
 */
@RestController
@RequestMapping("/v1/star")
@Api(tags = "收藏/点赞")
public class StarController {

    private final StarService starService;

    public StarController(StarService starService) {
        this.starService = starService;
    }

    @PostMapping
    @ApiOperation("添加")
    public ResponseEntity<Messager> add(@RequestBody @Valid StarAddDTO dto,BindingResult br) {
        starService.add(dto);
        return Responser.created();
    }


    @DeleteMapping
    @ApiOperation("删除")
    public ResponseEntity<Messager> delete(@RequestBody @Valid SingleId id, BindingResult br) {
        starService.delete(id.getId());
        return Responser.deleted();
    }

    @GetMapping("/find")
    @ApiOperation("分页")
    public ResponseEntity<Page<StarVO>> find(Pageable pageable) {
        Page<Star> stars = starService.find(pageable);
        Page<StarVO> vos = stars.map(StarVO::of);
        return Responser.ok(vos);
    }


}
