package com.mj.doc.article.controller;

import com.mj.core.data.del.SingleId;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.mj.doc.article.dto.ArticleAddDTO;
import com.mj.doc.article.dto.ArticleUpdateDTO;
import com.mj.doc.article.model.Article;
import com.mj.doc.article.vo.ArticleVO;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@RestController
@RequestMapping("/v1/article")
@Api(tags = "文章")
public class ArticleController {

    @PostMapping
    public ResponseEntity<Messager> add(@Valid @RequestBody ArticleAddDTO articleAddDTO, BindingResult br) {
        Article article = new Article();
        return Responser.created();
    }


    @PatchMapping
    public ResponseEntity<Messager> update(@Valid @RequestBody ArticleUpdateDTO articleUpdateDTO, BindingResult br) {
        Article article = new Article();
        return Responser.updated();
    }

    @DeleteMapping
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleId id, BindingResult br) {
        return Responser.deleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleVO> get() {

        ArticleVO vo = new ArticleVO();
        return Responser.ok(vo);
    }

}
