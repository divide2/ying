package com.ying.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author bvvy
 */
@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new MpFilter(),BasicAuthenticationFilter.class)
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/v1/**","/swagger**").permitAll()
                .anyRequest().authenticated();
    }


}
