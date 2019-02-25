package com.ying.team.service.impl;

import com.ying.auth.repo.AclRepository;

import com.ying.auth.vo.UserVO;
import com.ying.team.model.Acl;
import com.ying.team.repo.MemberRepository;
import com.ying.team.service.AclService;
import com.ying.team.vo.MenuVO;
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
    public List<UserVO> listTeamOwnMenuUsers(String teamId, String menuId) {
        return null;
    }

    @Override
    public List<MenuVO> listTeamUserMenus(String teamId, Integer userId) {
        // 获取菜单
        List<Acl> acls = aclRepository.findByTeamUser(teamId, userId);
        return null;
    }
}
