package com.divide2.team.vo;

import com.divide2.auth.vo.UserVO;
import com.divide2.core.root.VO;
import com.divide2.team.model.TeamJoinApplication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 * @date 2019/2/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamApplicationVO implements VO<TeamJoinApplication> {
    private String id;

    private String memoName;

    private String status;

    private UserVO user;
}
