package com.mj.core.er;

import com.mj.auth.principal.model.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 获取登录信息
 *
 * @author bvvy
 * @date 2018/7/28
 */
@Component
public class Loginer {

    private UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();

    public Integer userId() {
        return userDetails.getId();
    }

    public String username() {
        return userDetails.getUsername();
    }

    public static Integer companyId() {
        return 1;
    }
}
