package com.mj.auth.res.service;

import com.mj.auth.res.dto.OperAddDTO;
import com.mj.auth.res.model.Oper;
import com.mj.core.basic.service.BasicService;

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
