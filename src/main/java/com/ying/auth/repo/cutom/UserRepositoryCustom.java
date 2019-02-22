package com.ying.auth.repo.cutom;

import com.ying.auth.model.User;
import com.ying.team.vo.TeamVO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * @author bvvy
 * <p>
 * 用户repo
 */
public interface UserRepositoryCustom {



    /**
     * 通过登录账号获取用户
     * @param username 账号
     * @return user
     */
    User getByAccount(String username);



}
