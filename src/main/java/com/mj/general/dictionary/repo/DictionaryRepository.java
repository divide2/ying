package com.mj.general.dictionary.repo;

import com.mj.general.dictionary.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author zejun
 * @date 2018/7/30 09:20
 */
@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary,Integer>,QuerydslPredicateExecutor<Dictionary> {
}
