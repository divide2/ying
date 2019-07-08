package com.divide2.client.service;

import com.divide2.client.dto.UserInfoVO;

/**
 * @author bvvy
 * @date 2019/2/14
 */
public interface ClientService {
    /**
     * 查用户的信息 先查好友 不是好友 再查普通用户
     * @param userId user
     * @return info
     */
    UserInfoVO getUserInfo(Integer userId);
}
