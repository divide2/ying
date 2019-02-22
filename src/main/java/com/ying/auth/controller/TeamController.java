package com.ying.auth.controller;

import com.ying.auth.dto.*;
import com.ying.auth.model.Team;
import com.ying.auth.service.TeamService;
import com.ying.auth.vo.*;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.mine.vo.WarehouseVO;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.vo.OrderVO;
import com.ying.product.query.StockQuery;
import com.ying.product.vo.ProductVO;
import com.ying.product.vo.StockVO;
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

/**
 * @author bvvy
 */
@RestController
@RequestMapping("/v1/team")
@Api(tags = "团队")
public class TeamController {


    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
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
    public ResponseEntity<TeamVO> search(UserSearchDTO search) {
        TeamVO vo = teamService.search(search);
        return Responser.ok(vo);
    }

    @GetMapping("/{teamId}/users")
    @ApiOperation("获取成员")
    public ResponseEntity<List<TeamUserVO>> listGroupUsers(@PathVariable String teamId) {
        List<TeamUserVO> teamUsers = teamService.listGroupUsers(teamId);
        return Responser.ok(teamUsers);
    }

    @GetMapping("/{teamId}/applications")
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
    public ResponseEntity<Messager> confirm(@Valid @RequestBody TeamConfirmDTO dto, Errors errors ){
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
        List<TeamVO> cooperations= teamService.listTeamCooperations(teamId);
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
    public ResponseEntity<Messager> confirmCooperation(@Valid @RequestBody TeamCooperationConfirmDTO dto, Errors errors ){
        teamService.confirmCooperation(dto);
        return Responser.created();
    }



    @GetMapping("{teamId}/order/receive")
    @ApiOperation("获取团队收到的订单")
    public ResponseEntity<Page<OrderVO>> findReceiveOrder(@PathVariable String teamId, OrderQueryParam queryParam, Pageable pageable) {
        return Responser.ok(teamService.findReceiveOrder(teamId,queryParam, pageable));
    }


    @GetMapping("{teamId}/order/send")
    @ApiOperation("获取团对发送的订单，就是采购单")
    public ResponseEntity<Page<OrderVO>> findSendOrder(@PathVariable String teamId,OrderQueryParam queryParam, Pageable pageable) {
        return Responser.ok(teamService.findSendOrder(teamId,queryParam, pageable));
    }

    @GetMapping("{teamId}/stocks")
    @ApiOperation("团队库存")
    public ResponseEntity<Page<StockVO>> findStock(@PathVariable String teamId,Pageable pageable,  StockQuery stockQuery) {
        Page<StockVO> vo = teamService.findStock(teamId,stockQuery, pageable);
        return Responser.ok(vo);
    }

    @GetMapping("{teamId}/warehouses")
    @ApiOperation("团队仓库")
    public ResponseEntity<List<WarehouseVO>> listWarehouse(@PathVariable String teamId) {
        List<WarehouseVO> warehouses = teamService.listWarehouse(teamId);
        return Responser.ok(warehouses);
    }

    @GetMapping("{teamId}/products")
    @ApiOperation("团队产品")
    public ResponseEntity<Page<ProductVO>> product(@PathVariable String teamId,Pageable pageable) {
        Page<ProductVO> products = teamService.findProduct(teamId,pageable);
        return Responser.ok(products);
    }

}
