package com.mj.auth.res.service;

import com.mj.auth.res.dto.OperAddDTO;
import com.mj.auth.res.model.Oper;
import com.mj.core.service.BasicService;

/**
 * @author bvvy
 */
public interface OperService extends BasicService<Oper, Integer> {
    void add(OperAddDTO operDTO);
}
