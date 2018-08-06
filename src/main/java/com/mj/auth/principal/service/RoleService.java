package com.mj.auth.principal.service;

import com.mj.auth.principal.dto.RoleAddAuthDTO;
import com.mj.auth.principal.dto.RoleAddUsersDTO;
import com.mj.auth.principal.dto.RoleQueryDTO;
import com.mj.auth.principal.model.Role;
import com.mj.core.basic.service.BasicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 */
public interface RoleService extends BasicService<Role, Integer> {

    /**
     * 查询
     * @param query query
     * @param pageable page
     * @return page
     */

    Page<Role> find(RoleQueryDTO query, Pageable pageable);

    /**
     * 添加角色的用户
     * @param roleAddUsersDTO 角色用户
     */
    void addUsers(RoleAddUsersDTO roleAddUsersDTO);

    /**
     * 添加角色的菜单权限
     * @param roleAddAuthDTO 菜单
     */
    void addRoleAuth(RoleAddAuthDTO roleAddAuthDTO);

}
