package com.ying.team.vo;

import com.ying.auth.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 * @date 2019/2/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {

    private SquadVO role;
    private UserVO user;
}
