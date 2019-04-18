package com.divide2.auth.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bvvy
 * @date 2018/12/3
 */

public class PhoneLoginFilter extends AbstractAuthenticationProcessingFilter {

    public PhoneLoginFilter() {
        this("/login/phone");

    }

    public PhoneLoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String phoneNumber = request.getParameter("phoneNumber");
        String verificationCode = request.getParameter("verificationCode");
        Authentication authentication = new UsernamePasswordAuthenticationToken(phoneNumber,verificationCode);
        return null;
    }
}
