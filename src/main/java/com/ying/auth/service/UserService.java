package com.ying.auth.service;


import com.ying.auth.dto.UserQueryDTO;
import com.ying.auth.model.User;
import com.ying.auth.vo.UserVO;
import com.ying.core.basic.service.BasicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author bvvy
 */
public interface UserService extends BasicService<User,Integer> {

    /**
     * 通过用户名获取用户
     * @param username p
     * @return principal
     */
    User getByUsername(String username);


    /**
     * 校验用户名
     * @param username 用户名
     */
    void validUsername(String username);


    /**
     * 获取角色所有用户
     * @param roleId roleId
     * @return users
     */
    List<User> findUsersByRole(Integer roleId);

    /**
     * 用户分页查询
     * @param query queyr
     * @param pageable page
     * @return users
     */
    Page<User> find(UserQueryDTO query, Pageable pageable);

    /**
     * 通过微信open Id 获取
     * @param openid
     * @return
     */
    User getByWechatOpenId(String openid);

    UserVO getVO(Integer userId);

    UserVO getByAccount(String account);

}
