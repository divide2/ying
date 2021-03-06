package com.divide2.auth.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author bvvy
 *
 * spring seecurity userdetails
 */
@Data
@Builder
public class UserDetailsImpl implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String email;
    private String gender;
    private String avatar;
    private boolean enabled;
    @Builder.Default
    private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;


}
