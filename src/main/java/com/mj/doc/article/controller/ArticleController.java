package com.mj.doc.article.controller;

import com.mj.core.data.del.SingleId;
import com.mj.core.data.properties.StatusProperties;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.mj.doc.article.dto.ArticleAddDTO;
import com.mj.doc.article.dto.ArticleQueryDTO;
import com.mj.doc.article.dto.ArticleUpdateDTO;
import com.mj.doc.article.model.Article;
import com.mj.doc.article.service.ArticleService;
import com.mj.doc.article.vo.ArticleVO;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@RestController
@RequestMapping("/v1/article")
@Api(tags = "文章")
public class ArticleController {

    private final StatusProperties statusProperties;
    private final ArticleService articleService;

    public ArticleController(StatusProperties statusProperties,
                             ArticleService articleService) {
        this.statusProperties = statusProperties;
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<Messager> add(@Valid @RequestBody ArticleAddDTO articleAddDTO, BindingResult br) {

        articleService.add(articleAddDTO);
        return Responser.created();
    }

    @PatchMapping
    public ResponseEntity<Messager> update(@Valid @RequestBody ArticleUpdateDTO articleUpdateDTO, BindingResult br) {
        articleService.update(articleUpdateDTO);
        return Responser.updated();
    }

    @DeleteMapping
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleId id, BindingResult br) {
        articleService.delete(id.getId());
        return Responser.deleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleVO> get(@PathVariable Integer id) {
        Article article = articleService.get(id);
        ArticleVO vo = ArticleVO.fromArticle(article);
        return Responser.ok(vo);
    }

    @GetMapping("/find")
    public ResponseEntity<Page<ArticleVO>> find(ArticleQueryDTO articleQueryDTO, Pageable pageable) {
        Page<Article> articles = articleService.find(articleQueryDTO, pageable);
        Page<ArticleVO> vos = articles.map(ArticleVO::fromArticle);
        return Responser.ok(vos);
    }

}
