package com.divide2.friend.controller;

import com.divide2.core.data.resp.Messager;
import com.divide2.core.er.Responser;
import com.divide2.friend.dto.ApplyDTO;
import com.divide2.friend.dto.ConfirmDTO;
import com.divide2.friend.service.FriendService;
import com.divide2.friend.vo.ApplicationVO;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/30
 */
@RestController
@Api(tags = "好友")
@RequestMapping("/v1/friend")
public class FriendController {

    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/apply")
    public ResponseEntity<Messager> apply(@RequestBody @Valid ApplyDTO dto, Errors errors) {
        friendService.apply(dto);
        return Responser.created();
    }

    @GetMapping("/applications")
    public ResponseEntity<List<ApplicationVO>> listApplications() {
        List<ApplicationVO> vos = friendService.listApplications();
        return Responser.ok(vos);
    }

    @PostMapping("/confirm")
    public ResponseEntity<Messager> confirm(@RequestBody @Valid ConfirmDTO dto, Errors errors) {
        friendService.confirm(dto);
        return Responser.created();
    }


    @GetMapping("/applicatoin/{applicationId}")
    public ResponseEntity<ApplicationVO> getApplication(@PathVariable Integer applicationId) {
        ApplicationVO vo = friendService.getApplication(applicationId);
        return Responser.ok(vo);
    }
}
