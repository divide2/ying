package com.mj.doc.article.service.impl;

import com.mj.core.data.properties.StatusProperties;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.core.val.Punctuation;
import com.mj.doc.article.dto.ArticleAddDTO;
import com.mj.doc.article.dto.ArticleQueryDTO;
import com.mj.doc.article.dto.ArticleUpdateDTO;
import com.mj.doc.article.model.Article;
import com.mj.doc.article.repo.ArticleRepository;
import com.mj.doc.article.service.ArticleService;
import com.mj.doc.tag.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@Service
public class ArticleServiceImpl extends SimpleBasicServiceImpl<Article,Integer,ArticleRepository> implements ArticleService {
        private final StatusProperties statusProperties;
        private final ArticleRepository articleRepository;
        private final TagService tagService;

        public ArticleServiceImpl(StatusProperties statusProperties,
                                  ArticleRepository articleRepository,
                                  TagService tagService) {
                this.statusProperties = statusProperties;
                this.articleRepository = articleRepository;
                this.tagService = tagService;
        }

        @Override
        public Page<Article> find(ArticleQueryDTO articleQueryDTO, Pageable pageable) {
                return articleRepository.findAll(pageable);
        }

        @Override
        @Transactional(rollbackFor = Exception.class)
        public void add(ArticleAddDTO articleAddDTO) {
                Article article = Article.builder()
                        .content(articleAddDTO.getContent())
                        .expiry(articleAddDTO.getExpiry())
                        .createdDate(LocalDateTime.now())
                        .createdUserId(1)
                        .createdUsername("bvvy")
                        .deleted(statusProperties.getDisable())
                        .enabled(statusProperties.getEnable())
                        .publishStatus("publish")
                        .tags(StringUtils.join(articleAddDTO.getTags(), Punctuation.COMMA))
                        .title(articleAddDTO.getTitle())
                        .updatedDate(LocalDateTime.now())
                        .updatedUserId(1)
                        .updatedDate(LocalDateTime.now())
                        .updatedUsername("bvvy")
                        .build();
                tagService.add(articleAddDTO.getTags());
                this.add(article);
        }

        @Override
        @Transactional(rollbackFor = Exception.class)
        public void update(ArticleUpdateDTO articleUpdateDTO) {
                Article article = this.get(articleUpdateDTO.getId());
                article.setId(articleUpdateDTO.getId());
                article.setContent(articleUpdateDTO.getContent());
                article.setExpiry(articleUpdateDTO.getExpiry());
                article.setPublishStatus("publish");
                article.setTags(StringUtils.join(articleUpdateDTO.getTags(), Punctuation.COMMA));
                article.setTitle(articleUpdateDTO.getTitle());
                article.setUpdatedDate(LocalDateTime.now());
                article.setUpdatedUserId(1);
                article.setUpdatedDate(LocalDateTime.now());
                article.setUpdatedUsername("bvvy");
                tagService.add(articleUpdateDTO.getTags());
                articleRepository.save(article);
        }
}
