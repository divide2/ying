package com.mj.auth.principal.controller;

import com.mj.auth.principal.dto.UserAddDTO;
import com.mj.auth.principal.dto.UserUpdateDTO;
import com.mj.auth.principal.model.User;
import com.mj.auth.principal.service.UserService;
import com.mj.auth.principal.vo.UserVO;
import com.mj.core.data.del.SingleDelete;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public void add(@RequestBody UserAddDTO userAddDTO, BindingResult br) {
        User user = new User();
        userService.add(user);
    }

    @PatchMapping
    public ResponseEntity<Messager> update(@RequestBody UserUpdateDTO userUpdateDTO, BindingResult br) {
        User user = userService.get(userUpdateDTO.getId());
        userService.update(user);
        return Responser.updated();
    }

    @DeleteMapping
    public ResponseEntity<Messager> delete(SingleDelete<Integer> del) {
        userService.delete(del.getId());
        return Responser.deleted();
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserVO> get(@PathVariable Integer id) {
        User user = userService.get(id);
        UserVO userVO = new UserVO();
        return Responser.ok(userVO);
    }

}
