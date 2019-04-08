package com.ying.auth.manager;

import com.ying.core.er.VerificationCodeManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author bvvy
 * @date 2018/12/3
 */
public class MobileCodeAuthenticationManager implements AuthenticationManager {
    private final VerificationCodeManager verificationCodeManager = new VerificationCodeManager(aliyunProperties);
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();
        String credentials = (String) authentication.getCredentials();
        if (verificationCodeManager.validate(principal, credentials)) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new UsernamePasswordAuthenticationToken(principal, credentials);
        }
        throw new BadCredentialsException("验证码错误");
    }

}
