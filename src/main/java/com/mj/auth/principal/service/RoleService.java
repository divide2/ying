package com.mj.auth.principal.service;

import com.mj.auth.principal.dto.RoleQueryDTO;
import com.mj.auth.principal.model.Role;
import com.mj.core.service.BasicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService extends BasicService<Role, Integer> {

    /**
     * 查询
     * @param query query
     * @param pageable page
     * @return page
     */

    Page<Role> find(RoleQueryDTO query, Pageable pageable);
}
