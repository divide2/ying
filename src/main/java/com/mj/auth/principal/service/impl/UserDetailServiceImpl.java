package com.mj.auth.principal.service.impl;

import com.mj.auth.principal.model.User;
import com.mj.auth.principal.model.UserDetailsImpl;
import com.mj.auth.principal.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("not found");
        }
        List<SimpleGrantedAuthority> authorities = userRepository.findUserRolesByUsername(username);
        return UserDetailsImpl.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .nickname(user.getNickname())
                .avatar(user.getAvatar())
                .gender(user.getGender())
                .email(user.getEmail())
                .enabled(user.isEnabled())
                .phone(user.getPhone())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
    }

}


