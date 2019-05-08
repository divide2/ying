package com.divide2.auth.controller;

import com.divide2.auth.dto.UserAddDTO;
import com.divide2.auth.dto.UserQueryDTO;
import com.divide2.auth.dto.UserSearchDTO;
import com.divide2.auth.dto.UserUpdateDTO;
import com.divide2.auth.model.User;
import com.divide2.auth.service.UserService;
import com.divide2.auth.vo.UserVO;
import com.divide2.core.Exist;
import com.divide2.core.data.del.SingleId;
import com.divide2.core.data.resp.Messager;
import com.divide2.core.er.Responser;
import com.divide2.user.UserExist;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

/**
 * @author bvvy
 */
@RestController
@RequestMapping("/v1/user")
@Api(tags = "用户")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    @ApiOperation("添加")
    public void add(@Valid @RequestBody UserAddDTO userAddDTO, BindingResult br) {
        User user = User.builder()
                .username(userAddDTO.getUsername())
                .password(userAddDTO.getPassword())
                .nickname(userAddDTO.getNickname())
                .build();
        userService.add(user);
    }

    @PutMapping
    @ApiOperation("/self")
    public ResponseEntity<Messager> updateSelf(@Valid @RequestBody UserUpdateDTO dto, BindingResult br) {
        userService.update(dto);
        return Responser.updated();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleId del, BindingResult br) {
        userService.delete(del.getId());
        return Responser.deleted();
    }


    @GetMapping("/{id}")
    @ApiOperation("获取单个")
    public ResponseEntity<UserVO> get(@PathVariable Integer id) {
        UserVO user = userService.getVO(id);
        return Responser.ok(user);
    }

    @GetMapping("/search")
    @ApiOperation("查询")
    public ResponseEntity<UserVO> search(UserSearchDTO search) {
        return Responser.ok(userService.search(search));
    }

    @GetMapping("/find")
    @ApiOperation("获取分页")
    public ResponseEntity<Page<UserVO>> find(UserQueryDTO query, Pageable pageable) {
        Page<User> users = userService.find(query, pageable);
        return Responser.ok(users.map(UserVO::fromUser));
    }

    @GetMapping("/exist")
    @ApiOperation("是否存在")
    public ResponseEntity<Exist> exist(UserExist userExist) {
        Exist exist = userService.checkExist(userExist);
        return Responser.ok(exist);
    }

}
