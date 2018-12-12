package com.ying.core.er;

import com.ying.auth.model.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 获取登录信息
 *
 * @author bvvy
 * @date 2018/7/28
 */
public class Loginer {
    private static final UserDetailsImpl LoginUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    public static Integer userId() {
        return LoginUser.getId();
    }

    public static String username() {
        return LoginUser.getUsername();
    }

    public static String avatar() {
        return LoginUser.getAvatar();
    }

    public static Integer companyId() {
        return LoginUser.getCompanyId();
    }
}
