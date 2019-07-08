package com.divide2.basis.controller;

import com.divide2.basis.dto.StarAddDTO;
import com.divide2.basis.model.Star;
import com.divide2.basis.service.StarService;
import com.divide2.basis.vo.StarVO;
import com.divide2.core.data.del.SingleId;
import com.divide2.core.data.resp.Messager;
import com.divide2.core.er.Responser;
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
