package com.divide2.auth.manager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author bvvy
 * @date 2018/12/4
 */
public class VerificationCodeAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

//    private VerificationCodeManager verificationCodeManager = new VerificationCodeManager(aliyunProperties);

    private UserDetailsService userDetailsService;

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

//       try {
            UserDetails loadedUser = this.getUserDetailsService().loadUserByUsername(username);
//            if (verificationCodeManager.validate(username, (String) authentication.getCredentials())) {
                return loadedUser;
       /*      } else {
                throw new BadCredentialsException("验证码错误");
            }
        } catch (UsernameNotFoundException ex) {
            throw ex;
        }*/
    }
}
