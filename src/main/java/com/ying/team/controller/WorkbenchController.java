package com.ying.team.controller;

import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.team.dto.GroupMenuDTO;
import com.ying.team.dto.MenuGroupDTO;
import com.ying.team.service.MenuGroupService;
import com.ying.team.service.WorkbenchService;
import com.ying.team.vo.WorkbenchMenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 工作台
 * @author bvvy
 * @date 2019/2/27
 */
@RestController
@RequestMapping("/v1/workbench")
@Api(tags = "工作台")
public class WorkbenchController {

    private final WorkbenchService workbenchService;
    private final MenuGroupService menuGroupService;

    public WorkbenchController(WorkbenchService workbenchService,
                               MenuGroupService menuGroupService) {
        this.workbenchService = workbenchService;
        this.menuGroupService = menuGroupService;
    }

    @GetMapping("/{menuGroupId}/menus")
    public ResponseEntity<List<WorkbenchMenuVO>> listWorkbenchMenu(@PathVariable String menuGroupId) {
        List<WorkbenchMenuVO> shortcutTree = workbenchService.listWorkbenchMenu(menuGroupId);
        return Responser.ok(shortcutTree);

    }

    @PostMapping("/group")
    @ApiOperation("添加工作台的组")
    public ResponseEntity<Messager> addGroup(@RequestBody @Valid MenuGroupDTO dto, Errors errors) {
        menuGroupService.add(dto);
        return Responser.created();
    }

    @DeleteMapping("/group")
    @ApiOperation("删除工作台的组")
    public ResponseEntity<Messager> deleteGroup(@RequestBody @Valid MenuGroupDTO dto, Errors errors) {
        menuGroupService.add(dto);
        return Responser.created();
    }

    @PostMapping("/group/menu")
    @ApiOperation("添加组内的菜单")
    public ResponseEntity<Messager> addGroupMenu(@RequestBody @Valid GroupMenuDTO dto, Errors errors) {
        workbenchService.addGroupMenu(dto);
        return Responser.created();
    }

    @DeleteMapping("/group/menu")
    @ApiOperation("删除组内的菜单")
    public ResponseEntity<Messager> deleteGroupMenu(@RequestBody @Valid GroupMenuDTO dto, Errors errors) {
        workbenchService.deleteGroupMenu(dto);
        return Responser.created();
    }
}
