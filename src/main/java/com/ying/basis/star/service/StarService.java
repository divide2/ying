package com.ying.basis.star.service;

import com.ying.basis.star.dto.StarAddDTO;
import com.ying.basis.star.model.Star;
import com.ying.core.basic.service.BasicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/9/10
 */
public interface StarService extends BasicService<Star, Integer> {


    /**
     * 添加
     * @param dto d'to
     */
    void add(StarAddDTO dto);

    /**
     *
     * @param pageable pageable
     * @return stars
     */
    Page<Star> findByUser(Pageable pageable);
}
