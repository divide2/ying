package com.ying.friend.service;

import com.ying.core.basic.service.BasicService;
import com.ying.friend.dto.ApplyDTO;
import com.ying.friend.dto.ConfirmDTO;
import com.ying.friend.model.Friend;
import com.ying.friend.vo.ApplicationVO;
import com.ying.friend.vo.FriendVO;

import javax.validation.Valid;
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
