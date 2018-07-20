package com.mj.doc.article.service;

import com.mj.core.service.BasicService;
import com.mj.doc.article.dto.ArticleAddDTO;
import com.mj.doc.article.dto.ArticleQueryDTO;
import com.mj.doc.article.dto.ArticleUpdateDTO;
import com.mj.doc.article.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/7/19
 */
public interface ArticleService extends BasicService<Article,Integer> {
    /**
     * 条件查询分页
     * @param articleQueryDTO dto
     * @param pageable pageable
     * @return a
     */
    Page<Article> find(ArticleQueryDTO articleQueryDTO, Pageable pageable);

    /**
     * 添加
     * @param articleAddDTO dto
     */
    void add(ArticleAddDTO articleAddDTO);

    /**
     * 修改
     * @param articleUpdateDTO dto
     */
    void update(ArticleUpdateDTO articleUpdateDTO);
}
