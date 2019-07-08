package com.divide2.team.controller;

import com.divide2.auth.dto.UserSearchDTO;
import com.divide2.core.data.resp.Messager;
import com.divide2.core.er.Loginer;
import com.divide2.core.er.Responser;
import com.divide2.mine.vo.WarehouseVO;
import com.divide2.order.query.OrderQueryParam;
import com.divide2.order.vo.OrderVO;
import com.divide2.product.query.StockQuery;
import com.divide2.product.vo.ProductVO;
import com.divide2.product.vo.StockStreamVO;
import com.divide2.product.vo.StockVO;
import com.divide2.team.dto.*;
import com.divide2.team.model.Team;
import com.divide2.team.service.TeamService;
import com.divide2.team.service.WorkbenchService;
import com.divide2.team.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @author bvvy
 */
@RestController
@RequestMapping("/v1/team")
@Api(tags = "团队")
public class TeamController {


    private final TeamService teamService;
    private final WorkbenchService workbenchService;

    public TeamController(TeamService teamService, WorkbenchService workbenchService) {
        this.teamService = teamService;
        this.workbenchService = workbenchService;
    }

    @PostMapping
    @ApiOperation("添加")
    public ResponseEntity<Messager> add(@Valid @RequestBody TeamAddDTO dto, BindingResult br) {
        teamService.add(dto);
        return Responser.created();
    }

    @PatchMapping
    @ApiOperation("修改")
    public ResponseEntity<Messager> update(@Valid @RequestBody Team team, BindingResult br) {
        Team t = teamService.get(team.getId());
        teamService.update(t);
        return Responser.updated();
    }


    @GetMapping("/{id}")
    @ApiOperation("获取单个")
    public ResponseEntity<TeamVO> get(@PathVariable String id) {
        return Responser.ok(teamService.getVO(id));
    }

    @GetMapping("/search")
    @ApiOperation("搜索团队")
    public ResponseEntity<TeamVO> search(UserSearchDTO search) {
        TeamVO vo = teamService.search(search);
        return Responser.ok(vo);
    }

    @GetMapping("/{teamId}/users")
    @ApiOperation("获取成员")
    public ResponseEntity<List<MemberVO>> listGroupUsers(@PathVariable String teamId) {
        List<MemberVO> teamUsers = teamService.listMembers(teamId);
        return Responser.ok(teamUsers);
    }

    @GetMapping("/{teamId}/applications")
    @ApiOperation("团队申请")
    public ResponseEntity<List<TeamApplicationVO>> listGroupApplications(@PathVariable String teamId) {
        List<TeamApplicationVO> teamApplications = teamService.listTeamApplications(teamId);
        return Responser.ok(teamApplications);
    }

    @PostMapping("/invite")
    @ApiOperation("邀请")
    public ResponseEntity<Messager> invite(InviteDTO dto) {
        return Responser.created();
    }

    @PostMapping("/apply")
    @ApiOperation("申请加入团队")
    public ResponseEntity<Messager> apply(@Valid @RequestBody TeamApplyDTO dto, Errors errors) {
        teamService.apply(dto);
        return Responser.created();
    }

    @PostMapping("/confirm")
    @ApiOperation("确认加入团队")
    public ResponseEntity<Messager> confirm(@Valid @RequestBody TeamConfirmDTO dto, Errors errors) {
        teamService.confirm(dto);
        return Responser.created();
    }

    @GetMapping("/{teamId}/cooperation/applications")
    @ApiOperation("合作申请")
    public ResponseEntity<List<CooperationApplicationVO>> listCooperationApplication(@PathVariable String teamId) {
        List<CooperationApplicationVO> cooperationApplications = teamService.listTeamCooperationApplication(teamId);
        return Responser.ok(cooperationApplications);
    }

    @GetMapping("/{teamId}/cooperations")
    @ApiOperation("合作伙伴")
    public ResponseEntity<List<TeamVO>> listCooperations(@PathVariable String teamId) {
        List<TeamVO> cooperations = teamService.listTeamCooperations(teamId);
        return Responser.ok(cooperations);
    }

    @PostMapping("/cooperation/apply")
    @ApiOperation("申请合作")
    public ResponseEntity<Messager> applyCooperation(@Valid @RequestBody TeamCooperationApplyDTO dto, Errors errors) {
        teamService.applyCooperation(dto);
        return Responser.created();
    }

    @PostMapping("/cooperation/confirm")
    @ApiOperation("确认合作")
    public ResponseEntity<Messager> confirmCooperation(@Valid @RequestBody TeamCooperationConfirmDTO dto, Errors errors) {
        teamService.confirmCooperation(dto);
        return Responser.created();
    }


    @GetMapping("/{teamId}/order/receive")
    @ApiOperation("获取团队收到的订单")
    public ResponseEntity<Page<OrderVO>> findReceiveOrder(@PathVariable String teamId, OrderQueryParam queryParam, Pageable pageable) {
        return Responser.ok(teamService.findReceiveOrder(teamId, queryParam, pageable));
    }


    @GetMapping("/{teamId}/order/send")
    @ApiOperation("获取团对发送的订单，就是采购单")
    public ResponseEntity<Page<OrderVO>> findSendOrder(@PathVariable String teamId, OrderQueryParam queryParam, Pageable pageable) {
        return Responser.ok(teamService.findSendOrder(teamId, queryParam, pageable));
    }

    @GetMapping("/{teamId}/stocks")
    @ApiOperation("团队库存")
    public ResponseEntity<Page<StockVO>> findStock(@PathVariable String teamId, Pageable pageable, StockQuery stockQuery) {
        Page<StockVO> vo = teamService.findStock(teamId, stockQuery, pageable);
        return Responser.ok(vo);
    }

    @GetMapping("/{teamId}/stream")
    @ApiOperation("库存流水")
    public ResponseEntity<Page<StockStreamVO>> findStream(@PathVariable String teamId, StockQuery stockQuery, Pageable pageable) {
        Page<StockStreamVO> stockStream = teamService.findStockStream(teamId, stockQuery, pageable);
        return Responser.ok(stockStream);
    }




    @GetMapping("/{teamId}/warehouses")
    @ApiOperation("团队仓库")
    public ResponseEntity<List<WarehouseVO>> listWarehouse(@PathVariable String teamId) {
        List<WarehouseVO> warehouses = teamService.listWarehouse(teamId);
        return Responser.ok(warehouses);
    }

    @GetMapping("/{teamId}/products")
    @ApiOperation("团队产品")
    public ResponseEntity<Page<ProductVO>> product(@PathVariable String teamId, Pageable pageable) {
        Page<ProductVO> products = teamService.findProduct(teamId, pageable);
        return Responser.ok(products);
    }

    @GetMapping("/{teamId}/user/workbench")
    @ApiOperation("每个人的工作台")
    public ResponseEntity<List<WorkbenchVO>> getTeamUserWorkbench(@PathVariable String teamId) {
        List<WorkbenchVO> teamUserWorkbench = workbenchService.getTeamUserWorkbench(teamId, Loginer.userId());
        return Responser.ok(teamUserWorkbench);
    }

    @GetMapping("/{teamId}/workbench")
    @ApiOperation("整个团队的工作台")
    public ResponseEntity<List<WorkbenchVO>> getTeamWorkbench(@PathVariable String teamId) {
        List<WorkbenchVO> teamWorkbench = workbenchService.getTeamWorkbench(teamId);
        return Responser.ok(teamWorkbench);
    }

    @GetMapping("/{teamId}/type/{type}/principal/{principalId}/menus")
    @ApiOperation("整个团队的工作台")
    public ResponseEntity<Set<String>> listTeamUserMenus(@PathVariable String teamId, @PathVariable String type, @PathVariable String principalId) {
        Set<String> menuIds = teamService.listTeamUserMenus(teamId, type, principalId);
        return Responser.ok(menuIds);
    }

    @GetMapping("/{teamId}/user/authorities")
    @ApiOperation("整个团队的工作台")
    public ResponseEntity<Set<String>> listTeamUserAuthorities(@PathVariable String teamId) {
        Set<String> authorities = teamService.listMemberAuthorities(teamId);
        return Responser.ok(authorities);
    }
}
