package com.divide2.team.listener;

import com.divide2.core.er.Loginer;
import com.divide2.core.root.converter.Converter;
import com.divide2.product.dto.WarehouseDTO;
import com.divide2.product.service.WarehouseService;
import com.divide2.team.dto.AclDTO;
import com.divide2.team.model.*;
import com.divide2.team.service.AclService;
import com.divide2.team.service.MemberService;
import com.divide2.team.service.MenuService;
import com.divide2.team.service.SquadService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/3/5
 */
@Component
public class TeamListener implements Listener<Team> {
    private final SquadService squadService;
    private final MemberService memberService;
    private final MenuService menuService;
    private final WarehouseService warehouseService;
    private final AclService aclService;

    public TeamListener(SquadService squadService,
                        MemberService memberService,
                        MenuService menuService,
                        WarehouseService warehouseService,
                        AclService aclService) {
        this.squadService = squadService;
        this.memberService = memberService;
        this.menuService = menuService;
        this.warehouseService = warehouseService;
        this.aclService = aclService;
    }

    /**
     * 初始化菜单时需要
     *
     * @param team team创建时需要给它初始化的东西
     */
    @Override
    public void onCreate(Team team) {
        //设置小组和成员
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

// 初始化权限,权限全部给他

        AclDTO acl = new AclDTO();
        acl.setPrincipleId(Loginer.userId().toString());
        acl.setPrincipleType(Acl.USER_TYPE);
        acl.setTeamId(team.getId());
        List<String> menuIds = Converter.of(menuService.findAll()).convert(Menu::getId);
        acl.setMenuIds(menuIds);
        aclService.add(acl);

        // todo ? 看是否需要 初始化一部分的workbench
        Workbench workbench = new Workbench();
        MenuGroup menuGroup = new MenuGroup();
        menuGroup.setName("团队");
        menuGroup.setOrderNum(1);
        menuGroup.setTeamId(team.getId());
        workbench.setMenuGroupId(menuGroup.getId());
//        workbench.setMenuId();

        //初始化一个仓库
        WarehouseDTO warehouse = new WarehouseDTO();
        warehouse.setTeamId(team.getId());
        warehouse.setName("默认仓库");
        warehouse.setRemarks("默认的仓库");
        warehouseService.add(warehouse);

        //初始化一个商品分类
    }

    @Override
    public void onDelete(Team team) {

    }
}
