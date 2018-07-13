package com.mj.auth.acl.repo.custom;

import com.mj.auth.acl.model.Acl;

import java.util.List;

/**
 * @author bvvy
 */
public interface AclRepositoryCustom {


    /**
     * 获取角色能够访问的所有操作和菜单
     * 用来显示已经设置过的权限
     *
     * @param roleId 角色
     * @return resIds
     */
    List<Integer> findAllResAndOperByRole(Integer roleId);

    /**
     * 获取一个用户能够访问的所有权限
     *
     * @param userId user id
     * @return resIDs
     */
    List<Integer> findAllResAndOperByUser(Integer userId);

    /**
     * 获取用户所有的操作的code
     * 用户判断按钮权限
     *
     * @param userId userid
     * @return oper code
     */
    List<String> findAllOperCodeByUser(Integer userId);


    /**
     * 获取角色能够访问的全部菜单
     *
     * @param roleId 角色id
     * @return 菜单id
     */
    List<Integer> findMenuIdsByRole(Integer roleId);

    /**
     * 获取资源
     *
     * @param pid   pid
     * @param ptype ptype
     * @param rid   rid
     * @param rtype rtype
     * @return acl
     */
    Acl findResByPrincipal(Integer pid, String ptype, Integer rid, String rtype);
}
