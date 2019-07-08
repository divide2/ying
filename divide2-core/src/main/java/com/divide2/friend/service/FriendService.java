package com.divide2.friend.service;

import com.divide2.core.basic.service.BasicService;
import com.divide2.friend.dto.ApplyDTO;
import com.divide2.friend.dto.ConfirmDTO;
import com.divide2.friend.model.Friend;
import com.divide2.friend.vo.ApplicationVO;
import com.divide2.friend.vo.FriendVO;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/11
 */
public interface FriendService extends BasicService<Friend,Integer> {
    /**
     * 获取全部好友
     * @param fromId 我的id
     * @return haoyou
     */
    List<FriendVO> listByFromId(Integer fromId);

    /**
     * 获取好友信息
     * @param fromId 我的
     * @param toId 好友的
     * @return info
     */
    FriendVO getVO(Integer fromId, Integer toId);

    List<ApplicationVO> listApplications();

    /**
     * 申请加好友
     * @param dto 对方
     */
    void apply(ApplyDTO dto);

    /**
     * 确认加好友
     * @param dto dto
     */
    void confirm(ConfirmDTO dto);

    /**
     * application id
     * @param applicationId application id
     * @return xx
     */
    ApplicationVO getApplication(Integer applicationId);

}
