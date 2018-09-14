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
        return 1;
    }

    public static String username() {
        return "bvvy";
    }

    public static String avatar() {
        return "http://img4.imgtn.bdimg.com/it/u=3982063378,1910620738&fm=26&gp=0.jpg";
    }

    public static Integer companyId() {
        return 1;
    }
}
