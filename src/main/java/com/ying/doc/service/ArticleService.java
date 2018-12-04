package com.ying.doc.service;

import com.ying.core.basic.service.BasicService;
import com.ying.doc.dto.ArticleAddDTO;
import com.ying.doc.dto.ArticleQueryDTO;
import com.ying.doc.dto.ArticleUpdateDTO;
import com.ying.doc.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/7/19
 */
public interface ArticleService extends BasicService<Article,Integer> {
    /**
     * 获取自己的文章条件查询分页
     * @param articleQueryDTO dto
     * @param pageable pageable
     * @param publishStatus 发布状态
     * @return a
     */
    Page<Article> findOwn(String publishStatus,ArticleQueryDTO articleQueryDTO, Pageable pageable);

    /**
     * 获取全部的文章
     * @param articleQueryDTO dto
     * @param pageable page
     * @return article
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
