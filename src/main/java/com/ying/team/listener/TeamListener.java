package com.ying.team.listener;

import com.ying.core.er.Loginer;
import com.ying.product.model.Warehouse;
import com.ying.team.dto.AclDTO;
import com.ying.team.model.*;
import com.ying.team.service.MemberService;
import com.ying.team.service.MenuService;
import com.ying.team.service.SquadService;
import org.springframework.stereotype.Component;

/**
 * @author bvvy
 * @date 2019/3/5
 */
@Component
public class TeamListener implements Listener<Team> {
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

    /**
     * 初始化菜单时需要
     *
     * @param team team创建时需要给它初始化的东西
     */
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
//        acl.setMenuIds(menuService.fin);
        // todo 初始化菜单
        // 初始化一部分的workbench
        Workbench workbench = new Workbench();
        MenuGroup menuGroup = new MenuGroup();
        menuGroup.setName("团队");
        menuGroup.setOrderNum(1);
        menuGroup.setTeamId(team.getId());
        workbench.setMenuGroupId(menuGroup.getId());
//        workbench.setMenuId();
        //初始化一个仓库
        Warehouse warehouse = new Warehouse();
        warehouse.setTeamId(team.getId());
        warehouse.setName("默认仓库");
        warehouse.setEnabled(true);
        warehouse.setRemarks("默认的仓库");
        //初始化一个商品分类
    }

    @Override
    public void onDelete(Team team) {

    }
}
