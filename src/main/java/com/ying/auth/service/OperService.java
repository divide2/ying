package com.ying.auth.service;

import com.ying.auth.dto.OperAddDTO;
import com.ying.auth.model.Oper;
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
