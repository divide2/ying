package com.ying.basis.star.service;

import com.ying.basis.star.dto.StarAddDTO;
import com.ying.basis.star.model.Star;
import com.ying.core.basic.service.BasicService;

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

}
