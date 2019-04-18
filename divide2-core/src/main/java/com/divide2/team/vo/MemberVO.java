package com.divide2.team.vo;

import com.divide2.auth.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {

    private SquadVO squad;
    private List<UserVO> user;
}
