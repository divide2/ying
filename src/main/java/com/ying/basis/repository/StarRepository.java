package com.ying.basis.repository;

import com.ying.basis.model.Star;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/9/10
 */
public interface StarRepository extends JpaRepository<Star,Integer> {


    /**
     * 获取我的点赞
     * @param userId  userId
     * @param pageable p
     * @return star
     */
    Page<Star> findByFromId(Integer userId, Pageable pageable);
}
