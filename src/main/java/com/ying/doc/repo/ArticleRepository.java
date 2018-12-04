package com.ying.doc.repo;

import com.ying.doc.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author bvvy
 * @date 2018/7/19
 */
public interface ArticleRepository extends JpaRepository<Article,Integer> ,QuerydslPredicateExecutor<Article> {
}
