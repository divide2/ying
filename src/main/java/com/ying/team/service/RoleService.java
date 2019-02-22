package com.ying.team.service;

import com.ying.team.dto.RoleAddAuthDTO;
import com.ying.team.dto.RoleAddUsersDTO;
import com.ying.team.dto.RoleQueryDTO;
import com.ying.team.model.Role;
import com.ying.team.vo.RoleVO;
import com.ying.core.basic.service.BasicService;
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

    RoleVO getVO(Integer roleId);
}
