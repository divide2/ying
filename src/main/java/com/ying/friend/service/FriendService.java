package com.ying.friend.service;

import com.ying.core.basic.service.BasicService;
import com.ying.friend.model.Friend;
import com.ying.friend.vo.FriendVO;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/11
 */
public interface FriendService extends BasicService<Friend,Integer> {
    List<FriendVO> listByFromId(Integer fromId);

    FriendVO getVO(Integer fromId, Integer toId);
}
