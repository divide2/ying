package com.ying.basis.repo;

import com.ying.basis.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author zejun
 * @date 2018/7/30 09:20
 */
@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Integer>, QuerydslPredicateExecutor<Dictionary> {

    /**
     * 通过 group code he code 获取
     *
     * @param groupCode group code
     * @param code      code
     * @return dic
     */
    Dictionary getByGroupCodeAndCode(String groupCode, String code);
}
