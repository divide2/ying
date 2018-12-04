package com.ying.doc.service.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.ying.basis.service.TagService;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.data.properties.PublishStatusProperties;
import com.ying.core.data.properties.StatusProperties;
import com.ying.core.er.Loginer;
import com.ying.core.val.Punctuation;
import com.ying.doc.dto.ArticleAddDTO;
import com.ying.doc.dto.ArticleQueryDTO;
import com.ying.doc.dto.ArticleUpdateDTO;
import com.ying.doc.model.Article;
import com.ying.doc.model.QArticle;
import com.ying.doc.repo.ArticleRepository;
import com.ying.doc.service.ArticleService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.ying.core.er.Queryer.wrapperLike;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@Service
public class ArticleServiceImpl extends SimpleBasicServiceImpl<Article, Integer, ArticleRepository> implements ArticleService {
    private final StatusProperties statusProperties;
    private final ArticleRepository articleRepository;
    private final TagService tagService;
    private final PublishStatusProperties publishStatusProperties;

    public ArticleServiceImpl(StatusProperties statusProperties,
                              ArticleRepository articleRepository,
                              TagService tagService,
                              PublishStatusProperties publishStatusProperties) {
        this.statusProperties = statusProperties;
        this.articleRepository = articleRepository;
        this.tagService = tagService;
        this.publishStatusProperties = publishStatusProperties;
    }

    @Override
    public Page<Article> findOwn(String publishStatus, ArticleQueryDTO articleQueryDTO, Pageable pageable) {
        QArticle article = QArticle.article;
        BooleanExpression predicate = article.createdUserId.eq(Loginer.userId());
        predicate = predicate.and(queryPredicate(publishStatus, articleQueryDTO, predicate));
        return articleRepository.findAll(predicate, pageable);
    }

    @Override
    public Page<Article> find(ArticleQueryDTO articleQueryDTO, Pageable pageable) {
        BooleanExpression predicate = Expressions.ONE.eq(Expressions.ONE);
        predicate = predicate.and(queryPredicate(publishStatusProperties.getPublish(), articleQueryDTO, predicate));
        return articleRepository.findAll(predicate, pageable);
    }

    private Predicate queryPredicate(String publishStatus, ArticleQueryDTO articleQueryDTO, BooleanExpression predicate) {
        QArticle article = QArticle.article;
        if (StringUtils.isNotEmpty(publishStatus)) {
            predicate = predicate.and(article.publishStatus.eq(publishStatus));
        }
        if (StringUtils.isNotEmpty(articleQueryDTO.getTitle())) {
            predicate = predicate.and(
                    article.title.like(wrapperLike(articleQueryDTO.getTitle()))
                            .or(article.content.eq(wrapperLike(articleQueryDTO.getTitle())))
            );
        }
        if (StringUtils.isNotEmpty(articleQueryDTO.getTag())) {
            predicate = predicate.and(article.tags.like(wrapperLike(articleQueryDTO.getTag())));
        }
        if (CollectionUtils.isNotEmpty(articleQueryDTO.getPublishDates())) {
            predicate = predicate.and(
                    article.createdDate.goe(articleQueryDTO.getPublishDates().get(0))
                            .and(article.createdDate.loe(articleQueryDTO.getPublishDates().get(1)))
            );
        }
        return predicate;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ArticleAddDTO articleAddDTO) {
        Article article = Article.builder()
                .content(articleAddDTO.getContent())
                .expiry(articleAddDTO.getExpiry())
                .createdDate(LocalDateTime.now())
                .createdUserId(Loginer.userId())
                .createdUsername(Loginer.username())
                .deleted(statusProperties.getDisable())
                .enabled(statusProperties.getEnable())
                .publishStatus(publishStatusProperties.getPublish())
                .tags(StringUtils.join(articleAddDTO.getTags(), Punctuation.COMMA))
                .title(articleAddDTO.getTitle())
                .updatedDate(LocalDateTime.now())
                .updatedUserId(Loginer.userId())
                .updatedDate(LocalDateTime.now())
                .updatedUsername(Loginer.username())
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
        article.setPublishStatus(publishStatusProperties.getPublish());
        article.setTags(StringUtils.join(articleUpdateDTO.getTags(), Punctuation.COMMA));
        article.setTitle(articleUpdateDTO.getTitle());
        article.setUpdatedDate(LocalDateTime.now());
        article.setUpdatedUserId(Loginer.userId());
        article.setUpdatedDate(LocalDateTime.now());
        article.setUpdatedUsername(Loginer.username());
        tagService.add(articleUpdateDTO.getTags());
        articleRepository.save(article);
    }
}
