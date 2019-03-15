package com.ying.team.service.impl;

import com.ying.core.root.converter.Converter;
import com.ying.team.dto.AclDTO;
import com.ying.team.model.Acl;
import com.ying.team.model.Member;
import com.ying.team.repo.AclRepository;
import com.ying.team.repo.MemberRepository;
import com.ying.team.service.AclService;
import com.ying.team.service.MenuService;
import com.ying.team.vo.MenuVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author bvvy
 */
@Service
public class AclServiceImpl implements AclService {
    private final AclRepository aclRepository;
    private final MemberRepository memberRepository;
    private final MenuService menuService;

    public AclServiceImpl(AclRepository aclRepository,
                          MemberRepository memberRepository,
                          MenuService menuService) {
        this.aclRepository = aclRepository;
        this.memberRepository = memberRepository;
        this.menuService = menuService;
    }

    @Override
    @Transactional
    public void add(AclDTO dto) {
        // 先删除之前的
        aclRepository.deleteExists(dto.getTeamId(), dto.getPrincipleId(), dto.getPrincipleType());
        //再添加
        Set<String> authorities = Converter.of(menuService.findByIds(dto.getMenuIds())).toSet(MenuVO::getAuthority);
        authorities.forEach(authority -> {
            Acl acl = new Acl();
            acl.setAuthority(authority);
            acl.setPrincipleId(dto.getPrincipleId());
            acl.setPrincipleType(dto.getPrincipleType());
            acl.setTeamId(dto.getTeamId());
            aclRepository.save(acl);
        });
    }

    @Override
    public Set<Integer> listTeamOwnMenuUserIds(String teamId, String authority) {
        // 先获取principle
        List<Acl> pAcls = aclRepository.findByTeamIdAndAuthority(teamId, authority);
        Set<Integer> userIds = new HashSet<>();
        pAcls.forEach(acl -> {
                    if (acl.isSquadType()) {
                        List<Member> members = memberRepository.findByTeamIdAndSquadId(teamId, acl.getPrincipleId());
                        Set<Integer> squadUserIds = members.stream().map(Member::getUserId).collect(Collectors.toSet());
                        userIds.addAll(squadUserIds);
                    } else if (acl.isUserType()) {
                        userIds.add(Integer.parseInt(acl.getPrincipleId()));
                    }
                }
        );
        // 如果是squad 那就 取它下面的userIds
        return userIds;
    }

   @Override
    public Set<String> listTeamUserAuthorities(String teamId, Integer userId) {
        // 获取菜单
        List<Acl> userAcls = aclRepository.findByTeamUser(teamId, userId);
        Member member = memberRepository.getByTeamIdAndUserId(teamId, userId);
        List<Acl> squadAcls = aclRepository.findByTeamSquad(member.getTeamId(), member.getSquadId());
        Set<String> userMenus = userAcls.stream().map(Acl::getAuthority).collect(Collectors.toSet());
        Set<String> squadMenus = squadAcls.stream().map(Acl::getAuthority).collect(Collectors.toSet());
        userMenus.addAll(squadMenus);
        return userMenus;
    }

    @Override
    public Set<String> listOnlyTeamUserAuthorities(String teamId, Integer userId) {
        // 获取菜单
        List<Acl> userAcls = aclRepository.findByTeamUser(teamId, userId);
        return userAcls.stream().map(Acl::getAuthority).collect(Collectors.toSet());
    }
    @Override
    public Set<String> listOnlyTeamSquadAuthorities(String teamId, String squadId) {
        // 获取菜单
        List<Acl> squadAcls = aclRepository.findByTeamSquad(teamId, squadId);
        return squadAcls.stream().map(Acl::getAuthority).collect(Collectors.toSet());
    }
}
