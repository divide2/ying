package com.mj.doc.article.repo;

import com.mj.doc.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/7/19
 */
public interface ArticleRepository extends JpaRepository<Article,Integer> {
}
