package com.divide2.friend.service.impl;

import com.divide2.auth.vo.UserVO;
import com.divide2.core.basic.service.impl.SimpleBasicServiceImpl;
import com.divide2.core.er.Asserter;
import com.divide2.core.er.Loginer;
import com.divide2.core.root.converter.Converter;
import com.divide2.friend.dto.ApplyDTO;
import com.divide2.friend.dto.ConfirmDTO;
import com.divide2.friend.dto.SimpleMenuChatDTO;
import com.divide2.friend.model.Application;
import com.divide2.friend.model.Friend;
import com.divide2.friend.repo.ApplicationRepository;
import com.divide2.friend.repo.FriendRepository;
import com.divide2.friend.service.FriendConnectService;
import com.divide2.friend.service.FriendService;
import com.divide2.friend.vo.ApplicationVO;
import com.divide2.friend.vo.FriendVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2018/12/11
 */
@Service
public class FriendServiceImpl extends SimpleBasicServiceImpl<Friend, Integer, FriendRepository>
        implements FriendService {

    private final FriendRepository friendRepository;
    private final FriendConnectService friendConnectService;
    private final ApplicationRepository applicationRepository;


    public FriendServiceImpl(FriendRepository friendRepository,
                             FriendConnectService friendConnectService,
                             ApplicationRepository applicationRepository) {
        this.friendRepository = friendRepository;
        this.friendConnectService = friendConnectService;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<FriendVO> listByFromId(Integer fromId) {
        List<Friend> friends = friendRepository.findByFromId(fromId);
        return friends.stream().map(this::to).collect(Collectors.toList());
    }

    @Override
    public FriendVO getVO(Integer fromId, Integer toId) {
        Friend friend = friendRepository.getOnlyFriend(fromId, toId);
        if (friend == null) {
            return null;
        }
        return this.to(friend);
    }

    public FriendVO to(Friend friend) {
        UserVO friendInfo = friendConnectService.getUser(friend.getToId());
        FriendVO vo = new FriendVO();
        vo.setUserId(friend.getToId());
        vo.setMemoName(friend.getMemoName());
        vo.setAvatar(friendInfo.getAvatar());
        vo.setNickname(friendInfo.getNickname());
        vo.setGender(friendInfo.getGender());
        vo.setPhone(friendInfo.getPhone());
        vo.setUsername(friendInfo.getUsername());
        vo.setEmail(friendInfo.getEmail());
        return vo;
    }

    @Override
    public List<ApplicationVO> listApplications() {
        List<Application> applications = applicationRepository.findUserApplications(Loginer.userId());
        return Converter.of(applications).convert(this::toApplicationVO);
    }

    @Override
    @Transactional
    public void apply(ApplyDTO dto) {
        Application application = applicationRepository.getByFromIdAndToId(Loginer.userId(), dto.getToId());
        UserVO user = friendConnectService.getUser(dto.getToId());
        if (application == null) {
            application = new Application();
            application.setCreateTime(LocalDateTime.now());
            application.setRemarks(dto.getRemarks());
            application.setFromId(Loginer.userId());
            application.setToId(dto.getToId());
            application.setStatus("waiting_confirm");
            application.setMemoName(dto.getMemoName());
            if (StringUtils.isBlank(dto.getMemoName())) {
                application.setMemoName(user.getNickname());
            }
        }
        application.setUpdateTime(LocalDateTime.now());
        applicationRepository.save(application);
        friendConnectService.addChat(new SimpleMenuChatDTO("friend_apply", dto.getToId()));
    }

    @Override
    @Transactional
    public void confirm(ConfirmDTO dto) {
        Application application = applicationRepository.getByFromIdAndToId(dto.getToId(), Loginer.userId());
        Asserter.notNull(application);

        application.setUpdateTime(LocalDateTime.now());
        application.setStatus("finish");
        applicationRepository.save(application);
        Friend friend = new Friend();
        friend.setCreateTime(LocalDateTime.now());
        friend.setFromId(Loginer.userId());
        friend.setToId(dto.getToId());
        friend.setMemoName(dto.getMemoName());
        this.add(friend);
        friend = new Friend();
        friend.setFromId(application.getFromId());
        friend.setToId(application.getToId());
        friend.setMemoName(application.getMemoName());
        friend.setCreateTime(LocalDateTime.now());
        this.add(friend);
    }

    @Override
    public ApplicationVO getApplication(Integer applicationId) {
        Application application = applicationRepository.getOne(applicationId);
        return toApplicationVO(application);
    }

    private ApplicationVO toApplicationVO(Application application) {
        ApplicationVO vo = new ApplicationVO();
        vo.setId(application.getId());
        vo.setRemarks(application.getRemarks());
        vo.setStatus(application.getStatus());
        UserVO user = null;
        if (application.getFromId().equals(Loginer.userId())) {
            user = friendConnectService.getUser(application.getToId());
            vo.setSelfApply(true);
        }
        if (application.getToId().equals(Loginer.userId())) {
            user = friendConnectService.getUser(application.getFromId());
        }
        vo.setUser(user);
        return vo;
    }

}
