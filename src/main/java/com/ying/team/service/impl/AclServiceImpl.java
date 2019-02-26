package com.ying.team.service.impl;

import com.ying.auth.repo.AclRepository;
import com.ying.core.root.converter.Converter;
import com.ying.team.dto.AclDTO;
import com.ying.team.model.Acl;
import com.ying.team.model.Member;
import com.ying.team.model.Menu;
import com.ying.team.repo.MemberRepository;
import com.ying.team.repo.MenuRepository;
import com.ying.team.service.AclService;
import com.ying.team.vo.MenuVO;
import org.springframework.stereotype.Service;

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
    private final MenuRepository menuRepository;

    public AclServiceImpl(AclRepository aclRepository,
                          MemberRepository memberRepository,
                          MenuRepository menuRepository
    ) {
        this.aclRepository = aclRepository;
        this.memberRepository = memberRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public void add(AclDTO dto) {
        // 先删除之前的
        aclRepository.deleteExists(dto.getTeamId(), dto.getPrincipleId(), dto.getPrincipleType());
        //再添加
        dto.getMenuIds()
                .forEach(menuId -> {
                    Acl acl = new Acl();
                    acl.setMenuId(menuId);
                    acl.setPrincipleId(dto.getPrincipleId());
                    acl.setPrincipleType(dto.getPrincipleType());
                    acl.setTeamId(dto.getTeamId());
                    aclRepository.save(acl);
                });
    }

    @Override
    public Set<Integer> listTeamOwnMenuUserIds(String teamId, String menuId) {
        // 先获取principle
        List<Acl> pAcls = aclRepository.findByTeamIdAndMenuId(teamId, menuId);
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
    public List<MenuVO> listTeamUserMenus(String teamId, Integer userId) {
        // 获取菜单
        List<Acl> userAcls = aclRepository.findByTeamUser(teamId, userId);
        Member member = memberRepository.getByTeamIdAndUserId(teamId, userId);
        List<Acl> squadAcls = aclRepository.findByTeamSquad(member.getTeamId(), member.getSquadId());
        Set<String> userMenus = userAcls.stream().map(Acl::getMenuId).collect(Collectors.toSet());
        Set<String> squadMenus = squadAcls.stream().map(Acl::getMenuId).collect(Collectors.toSet());
        userMenus.addAll(squadMenus);
        List<Menu> menus = menuRepository.findByIdIn(userMenus);
        return Converter.of(menus).convert(menu -> new MenuVO(
                menu.getId(),
                menu.getName(),
                menu.getPid(),
                menu.getPath(),
                menu.getEnabled(),
                menu.getOrderNum(),
                menu.getIcon(),
                menu.getCode()
        ));
    }
}
