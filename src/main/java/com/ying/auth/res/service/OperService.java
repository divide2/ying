package com.ying.auth.res.service;

import com.ying.auth.res.dto.OperAddDTO;
import com.ying.auth.res.model.Oper;
import com.ying.core.basic.service.BasicService;

/**
 * @author bvvy
 */
public interface OperService extends BasicService<Oper, Integer> {
    /**
     * 新增
     * @param operDTO
     */
    void add(OperAddDTO operDTO);
}
