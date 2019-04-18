package com.divide2.auth.service;


import com.divide2.auth.dto.PwdFindDTO;
import com.divide2.auth.dto.UserQueryDTO;
import com.divide2.auth.dto.UserSearchDTO;
import com.divide2.auth.dto.UserUpdateDTO;
import com.divide2.auth.model.User;
import com.divide2.auth.vo.UserVO;
import com.divide2.core.basic.service.BasicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    /**
     * 获取用户的详情信息
     * @param userId 用户
     * @return x
     */
    UserVO getVO(Integer userId);

    UserVO getByAccount(String account);


    UserVO search(UserSearchDTO search);

    void findPwd(PwdFindDTO pwdFind);

    void update(UserUpdateDTO dto);

}
