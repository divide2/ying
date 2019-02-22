package com.ying.team.service.impl;

import com.ying.auth.repo.AclRepository;

import com.ying.team.repo.MemberRepository;
import com.ying.team.service.AclService;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 */
@Service
public class AclServiceImpl implements AclService {
    private final AclRepository aclRepository;
    private final MemberRepository memberRepository;

    public AclServiceImpl(AclRepository aclRepository,
                          MemberRepository memberRepository) {
        this.aclRepository = aclRepository;
        this.memberRepository = memberRepository;
    }



}
