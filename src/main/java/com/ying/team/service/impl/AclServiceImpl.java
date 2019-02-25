package com.ying.team.service.impl;

import com.ying.auth.repo.AclRepository;

import com.ying.auth.vo.UserVO;
import com.ying.team.repo.MemberRepository;
import com.ying.team.service.AclService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<UserVO> listTeamOwnMenuUsers(String teamId, Integer menuId) {
        return null;
    }
}
