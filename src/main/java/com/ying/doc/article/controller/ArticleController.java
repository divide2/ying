package com.ying.doc.article.controller;

import com.ying.core.data.del.SingleId;
import com.ying.core.data.properties.StatusProperties;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.doc.article.dto.ArticleAddDTO;
import com.ying.doc.article.dto.ArticleQueryDTO;
import com.ying.doc.article.dto.ArticleUpdateDTO;
import com.ying.doc.article.model.Article;
import com.ying.doc.article.service.ArticleService;
import com.ying.doc.article.vo.ArticleVO;
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
    @ApiOperation("添加")
    public ResponseEntity<Messager> add(@Valid @RequestBody ArticleAddDTO articleAddDTO, BindingResult br) {

        articleService.add(articleAddDTO);
        return Responser.created();

    }

    @PatchMapping
    @ApiOperation("修改")
    public ResponseEntity<Messager> update(@Valid @RequestBody ArticleUpdateDTO articleUpdateDTO, BindingResult br) {
        articleService.update(articleUpdateDTO);
        return Responser.updated();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleId id, BindingResult br) {
        articleService.delete(id.getId());
        return Responser.deleted();
    }

    @GetMapping("/{id}")
    @ApiOperation("获取一篇文章")
    public ResponseEntity<ArticleVO> get(@PathVariable Integer id) {
        Article article = articleService.get(id);
        ArticleVO vo = ArticleVO.fromArticle(article);
        return Responser.ok(vo);
    }

    @GetMapping("/find")
    @ApiOperation("获取全部文章已发布的")
    public ResponseEntity<Page<ArticleVO>> find(ArticleQueryDTO articleQueryDTO, Pageable pageable) {
        Page<Article> articles = articleService.find(articleQueryDTO, pageable);
        Page<ArticleVO> vos = articles.map(ArticleVO::fromArticle);
        return Responser.ok(vos);
    }

    @GetMapping("/find/own/{publishStatus}")
    @ApiOperation("获取我的全部文章")
    public ResponseEntity<Page<ArticleVO>> findSelf(@PathVariable String publishStatus, ArticleQueryDTO articleQueryDTO, Pageable pageable) {
        Page<Article> articles = articleService.findOwn(publishStatus, articleQueryDTO, pageable);
        Page<ArticleVO> vos = articles.map(ArticleVO::fromArticle);
        return Responser.ok(vos);
    }

}
