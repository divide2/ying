package com.ying.auth.service.impl;

import com.ying.auth.model.User;
import com.ying.auth.model.UserCompany;
import com.ying.auth.model.UserDetailsImpl;
import com.ying.auth.repo.UserCompanyRepository;
import com.ying.auth.repo.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserCompanyRepository userCompanyRepository;

    public UserDetailServiceImpl(UserRepository userRepository,
                                 UserCompanyRepository userCompanyRepository) {
        this.userRepository = userRepository;
        this.userCompanyRepository = userCompanyRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getByAccount(username);
        if (user == null) {
            throw new UsernameNotFoundException("not found");
        }
        List<SimpleGrantedAuthority> authorities = userRepository.findUserRolesByUsername(username);
        UserCompany userCompany = userCompanyRepository.getByUserId(user.getId());
        Integer companyId = null;
        if (userCompany != null) {
            companyId = userCompany.getCompanyId();
        }
        return UserDetailsImpl.builder()
                .id(user.getId())
                .companyId(companyId)
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


