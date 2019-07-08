package com.divide2.basis.service;

import com.divide2.basis.dto.StarAddDTO;
import com.divide2.basis.model.Star;
import com.divide2.core.basic.service.BasicService;
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
