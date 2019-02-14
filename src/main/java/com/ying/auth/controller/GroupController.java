package com.ying.auth.controller;

import com.ying.auth.dto.GroupAddDTO;
import com.ying.auth.model.Group;
import com.ying.auth.service.GroupService;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author bvvy
 */
@RestController
@RequestMapping("/v1/group")
@Api(tags = "用户")
public class GroupController {


    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    @ApiOperation("添加")
    public void add(@Valid @RequestBody GroupAddDTO dto, BindingResult br) {
        groupService.add(dto);
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
    public ResponseEntity<Group> get(@PathVariable String id) {
        Group group = groupService.get(id);
//        Group groupVO = GroupVO.fromGroup(group);
        return Responser.ok(group);
    }

}
