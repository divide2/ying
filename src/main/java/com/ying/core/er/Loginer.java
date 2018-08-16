package com.ying.core.er;

import com.ying.auth.principal.model.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 获取登录信息
 *
 * @author bvvy
 * @date 2018/7/28
 */
public class Loginer {
    private static final UserDetailsImpl USER = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    public static Integer userId() {
        return USER.getId();
    }

    public static String username() {
        return USER.getUsername();
    }

    public static Integer companyId() {
        return 1;
    }
}
