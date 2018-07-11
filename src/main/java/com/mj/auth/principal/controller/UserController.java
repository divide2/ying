package com.mj.auth.principal.controller;

import com.mj.auth.principal.dto.UserAddDTO;
import com.mj.auth.principal.dto.UserUpdateDTO;
import com.mj.auth.principal.model.User;
import com.mj.auth.principal.service.UserService;
import com.mj.auth.principal.vo.UserVO;
import com.mj.core.data.del.SingleDelete;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author bvvy
 */
@RestController
@RequestMapping("/v1/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void add(@Valid @RequestBody UserAddDTO userAddDTO, BindingResult br) {
        User user = User.builder()
                        .username(userAddDTO.getUsername())
                        .password(userAddDTO.getPassword())
                        .nickname(userAddDTO.getNickname())
                        .build();
        userService.add(user);
    }

    @PatchMapping
    public ResponseEntity<Messager> update(@Valid @RequestBody UserUpdateDTO userUpdateDTO, BindingResult br) {
        User user = userService.get(userUpdateDTO.getId());
        userService.update(user);
        return Responser.updated();
    }

    @DeleteMapping
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleDelete del,BindingResult br) {
        userService.delete(del.getId());
        return Responser.deleted();
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserVO> get(@PathVariable Integer id) {
        User user = userService.get(id);
        UserVO userVO = toUserVO(user);
        return Responser.ok(userVO);
    }

    @GetMapping("/find")
    public ResponseEntity<Page<UserVO>> find(Pageable pageable) {
        Page<User> users = userService.find(pageable);
        return Responser.ok(users.map(this::toUserVO));
    }

    private UserVO toUserVO(User user) {
        return UserVO.builder()
                .id(user.getId())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .enabled(user.isEnabled())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .phone(user.getPhone())
                .username(user.getUsername())
                .build();
    }

}
