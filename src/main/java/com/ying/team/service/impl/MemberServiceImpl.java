package com.ying.team.service.impl;

import com.ying.team.model.Member;
import com.ying.team.repo.MemberRepository;
import com.ying.team.service.MemberService;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2019/2/13
 */
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public String getTeamUserSquadId(String teamId, Integer userId) {
        Member member = memberRepository.getByTeamIdAndUserId(teamId, userId);
        return member.getSquadId();
    }

}
