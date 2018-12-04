package com.ying.basis.repo;

import com.ying.basis.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/7/19
 */
public interface TagRepository extends JpaRepository<Tag,Integer> {

    /**
     * 根据名称获取
     * @param name 名称
     * @return Tag
     */
    Tag findByName(String name);
}
