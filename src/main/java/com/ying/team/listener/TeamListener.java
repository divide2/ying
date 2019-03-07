package com.ying.team.listener;

import com.ying.core.er.Loginer;
import com.ying.team.dto.AclDTO;
import com.ying.team.model.Acl;
import com.ying.team.model.Member;
import com.ying.team.model.Squad;
import com.ying.team.model.Team;
import com.ying.team.service.MemberService;
import com.ying.team.service.MenuService;
import com.ying.team.service.SquadService;
import org.springframework.stereotype.Component;

/**
 * @author bvvy
 * @date 2019/3/5
 */
@Component
public class TeamListener implements Listener<Team>{
    private final SquadService squadService;
    private final MemberService memberService;
    private final MenuService menuService;

    public TeamListener(SquadService squadService,
                        MemberService memberService,
                        MenuService menuService) {
        this.squadService = squadService;
        this.memberService = memberService;
        this.menuService = menuService;
    }

    @Override
    public void onCreate(Team team) {
        Squad squad = new Squad();
        squad.setName("默认小队");
        squad.setTeamId(team.getId());
        squadService.add(squad);
        Member member = new Member();
        member.setUserId(Loginer.userId());
        member.setTeamId(team.getId());
        member.setPosition("管理员");
        member.setSquadId(squad.getId());
        memberService.add(member);
        AclDTO acl = new AclDTO();
        acl.setPrincipleId(Loginer.userId().toString());
        acl.setPrincipleType(Acl.USER_TYPE);
        acl.setTeamId(team.getId());
        // todo 初始化菜单
//        acl.setMenuIds(menuService.fin);

    }

    @Override
    public void onDelete(Team team) {

    }
}
