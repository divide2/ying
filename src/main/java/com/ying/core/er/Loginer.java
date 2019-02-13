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

    public static Integer userId() {
        return loginUser().getId();
    }

    public static String username() {
        return loginUser().getUsername();
    }

    public static String avatar() {
        return loginUser().getAvatar();
    }

    public static Integer companyId() {


        return 0;
    }

    private static UserDetailsImpl loginUser() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
