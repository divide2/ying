package com.mj.auth.acl.repo.custom;

import java.util.List;

/**
 * @author bvvy
 */
public interface AclRepositoryCustom {


    /**
     * 获取角色能够访问的所有操作和菜单
     * 用来显示已经设置过的权限
     * @param roleId 角色
     * @return resIds
     */
    List<Integer> findAllResAndOperByRole(Integer roleId);

    /**
     * 获取一个用户能够访问的所有权限
     * @param userId user id
     * @return resIDs
     */
    List<Integer> findAllResAndOperByUser(Integer userId);

    /**
     * 获取用户所有的操作的code
     * 用户判断按钮权限
     * @param userId userid
     * @return oper code
     */
    List<String> findAllOperCodeByUser(Integer userId);


    List<Integer> findMenuIdsByRole(Integer roleId);
}
