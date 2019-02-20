package com.ying.auth.controller;

import com.ying.auth.dto.*;
import com.ying.auth.model.Group;
import com.ying.auth.service.GroupService;
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
@RequestMapping("/v1/group")
@Api(tags = "团队")
public class GroupController {


    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    @ApiOperation("添加")
    public ResponseEntity<Messager> add(@Valid @RequestBody GroupAddDTO dto, BindingResult br) {
        groupService.add(dto);
        return Responser.created();
    }

    @PatchMapping
    @ApiOperation("修改")
    public ResponseEntity<Messager> update(@Valid @RequestBody Group group, BindingResult br) {
        Group t = groupService.get(group.getId());
        groupService.update(t);
        return Responser.updated();
    }


    @GetMapping("/{id}")
    @ApiOperation("获取单个")
    public ResponseEntity<GroupVO> get(@PathVariable String id) {
        return Responser.ok(groupService.getVO(id));
    }

    @GetMapping("/search")
    public ResponseEntity<GroupVO> search(UserSearchDTO search) {
        GroupVO vo = groupService.search(search);
        return Responser.ok(vo);
    }

    @GetMapping("/{groupId}/users")
    @ApiOperation("获取成员")
    public ResponseEntity<List<GroupUserVO>> listGroupUsers(@PathVariable String groupId) {
        List<GroupUserVO> groupUsers = groupService.listGroupUsers(groupId);
        return Responser.ok(groupUsers);
    }

    @GetMapping("/{groupId}/applications")
    public ResponseEntity<List<GroupApplicationVO>> listGroupApplications(@PathVariable String groupId) {
        List<GroupApplicationVO> groupApplications = groupService.listGroupApplications(groupId);
        return Responser.ok(groupApplications);
    }
    @PostMapping("/invite")
    @ApiOperation("邀请")
    public ResponseEntity<Messager> invite(InviteDTO dto) {
        return Responser.created();
    }

    @PostMapping("/apply")
    @ApiOperation("申请加入团队")
    public ResponseEntity<Messager> apply(@Valid @RequestBody GroupApplyDTO dto, Errors errors) {
        groupService.apply(dto);
        return Responser.created();
    }

    @PostMapping("/confirm")
    @ApiOperation("确认加入团队")
    public ResponseEntity<Messager> confirm(@Valid @RequestBody GroupConfirmDTO dto, Errors errors ){
        groupService.confirm(dto);
        return Responser.created();
    }

    @GetMapping("/{groupId}/application/cooperation")
    @ApiOperation("合作申请")
    public ResponseEntity<List<CooperationApplicationVO>> listCooperationApplication(@PathVariable String groupId) {
        List<CooperationApplicationVO> cooperationApplications = groupService.listGroupCooperationApplication(groupId);
        return Responser.ok(cooperationApplications);
    }

    @PostMapping("//apply")
    @ApiOperation("申请合作")
    public ResponseEntity<Messager> applyCooperation(@Valid @RequestBody GroupCooperationApplyDTO dto, Errors errors) {
        groupService.applyCooperation(dto);
        return Responser.created();
    }

    @PostMapping("/confirm")
    @ApiOperation("确认合作")
    public ResponseEntity<Messager> confirmCooperation(@Valid @RequestBody GroupCooperationConfirmDTO dto, Errors errors ){
        groupService.confirmCooperation(dto);
        return Responser.created();
    }



    @GetMapping("{groupId}/order/receive")
    @ApiOperation("获取团队收到的订单")
    public ResponseEntity<Page<OrderVO>> findReceiveOrder(@PathVariable String groupId, OrderQueryParam queryParam, Pageable pageable) {
        return Responser.ok(groupService.findReceiveOrder(groupId,queryParam, pageable));
    }


    @GetMapping("{groupId}/order/send")
    @ApiOperation("获取团对发送的订单，就是采购单")
    public ResponseEntity<Page<OrderVO>> findSendOrder(@PathVariable String groupId,OrderQueryParam queryParam, Pageable pageable) {
        return Responser.ok(groupService.findSendOrder(groupId,queryParam, pageable));
    }

    @GetMapping("{groupId}/stocks")
    @ApiOperation("团队库存")
    public ResponseEntity<Page<StockVO>> findStock(@PathVariable String groupId,Pageable pageable,  StockQuery stockQuery) {
        Page<StockVO> vo = groupService.findStock(groupId,stockQuery, pageable);
        return Responser.ok(vo);
    }

    @GetMapping("{groupId}/warehouses")
    @ApiOperation("团队仓库")
    public ResponseEntity<List<WarehouseVO>> listWarehouse(@PathVariable String groupId) {
        List<WarehouseVO> warehouses = groupService.listWarehouse(groupId);
        return Responser.ok(warehouses);
    }

    @GetMapping("{groupId}/products")
    @ApiOperation("团队产品")
    public ResponseEntity<Page<ProductVO>> product(@PathVariable String groupId,Pageable pageable) {
        Page<ProductVO> products = groupService.findProduct(groupId,pageable);
        return Responser.ok(products);
    }

}
