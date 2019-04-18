package com.divide2.friend.vo;

import com.divide2.auth.vo.UserVO;
import com.divide2.core.root.VO;
import com.divide2.friend.model.Application;
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
