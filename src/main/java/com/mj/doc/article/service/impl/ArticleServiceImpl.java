package com.mj.doc.article.service.impl;

import com.mj.core.service.impl.SimpleBasicServiceImpl;
        import com.mj.doc.article.model.Article;
        import com.mj.doc.article.repo.ArticleRepository;
        import com.mj.doc.article.service.ArticleService;
        import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@Service
public class ArticleServiceImpl extends SimpleBasicServiceImpl<Article,Integer,ArticleRepository> implements ArticleService {

}
