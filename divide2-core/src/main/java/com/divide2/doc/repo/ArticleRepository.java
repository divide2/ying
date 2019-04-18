package com.divide2.doc.repo;

import com.divide2.doc.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author bvvy
 * @date 2018/7/19
 */
public interface ArticleRepository extends JpaRepository<Article,Integer> ,QuerydslPredicateExecutor<Article> {
}
