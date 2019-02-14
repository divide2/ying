package com.ying.friend.vo;

import com.ying.auth.vo.UserVO;
import lombok.Data;

/**
 * @author bvvy
 * @date 2019/2/14
 */
@Data
public class ApplicationVO {

    private String remarks;

    private String status;

    private UserVO user;
}
