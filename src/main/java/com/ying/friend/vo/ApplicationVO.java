package com.ying.friend.vo;

import com.ying.auth.vo.UserVO;
import com.ying.core.root.VO;
import com.ying.friend.model.Application;
import lombok.Data;

/**
 * @author bvvy
 * @date 2019/2/14
 */
@Data
public class ApplicationVO implements VO<Application> {
    private Integer id;

    private String remarks;

    private String status;

    private UserVO user;

    private boolean selfApply;
}
