package com.ying.auth.service.impl;

import com.ying.auth.dto.GroupAddDTO;
import com.ying.auth.dto.GroupApplyDTO;
import com.ying.auth.dto.GroupConfirmDTO;
import com.ying.auth.model.Group;
import com.ying.auth.model.GroupApplication;
import com.ying.auth.model.UserGroupRole;
import com.ying.auth.repo.GroupApplicationRepository;
import com.ying.auth.repo.GroupRepository;
import com.ying.auth.repo.UserGroupRoleRepository;
import com.ying.auth.service.GroupInnerConnectService;
import com.ying.auth.service.GroupService;
import com.ying.auth.vo.GroupUserVO;
import com.ying.auth.vo.GroupVO;
import com.ying.auth.vo.RoleVO;
import com.ying.auth.vo.UserVO;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.er.Asserter;
import com.ying.core.er.Loginer;
import com.ying.core.root.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/13
 */
@Service
public class GroupServiceImpl extends SimpleBasicServiceImpl<Group, String, GroupRepository> implements GroupService {
    private final UserGroupRoleRepository userGroupRoleRepository;
    private final GroupRepository groupRepository;
    private final GroupInnerConnectService groupInnerConnectService;
    private final GroupApplicationRepository groupApplicationRepository;

    public GroupServiceImpl(UserGroupRoleRepository userGroupRoleRepository,
                            GroupRepository groupRepository,
                            GroupInnerConnectService groupInnerConnectService,
                            GroupApplicationRepository groupApplicationRepository) {
        this.userGroupRoleRepository = userGroupRoleRepository;
        this.groupRepository = groupRepository;
        this.groupInnerConnectService = groupInnerConnectService;
        this.groupApplicationRepository = groupApplicationRepository;
    }

    @Override
    public GroupVO getVO(String groupId) {
        Group group = this.get(groupId);
        return new GroupVO(
                group.getId(),
                group.getName(),
                group.getImage()
        );
    }

    @Override
    @Transactional
    public void add(GroupAddDTO dto) {
        this.checkGroupExists(dto.getName());
        Group group = new Group();
        group.setName(dto.getName());
        this.add(group);
        UserGroupRole userGroupRole = new UserGroupRole();
        userGroupRole.setUserId(Loginer.userId());
        userGroupRole.setGroupId(group.getId());
        // todo 管理员的id
        userGroupRole.setRoleId(1);
        userGroupRoleRepository.save(userGroupRole);
    }

    private void checkGroupExists(String name) {
        Group group = groupRepository.getByName(name);
        Asserter.isNull(group);
    }

    @Override
    public List<GroupUserVO> listGroupUsers(String groupId) {
        this.getVO(groupId);
        List<UserGroupRole> groupUsers = userGroupRoleRepository.findByGroupId(groupId);
        return Converter.of(groupUsers).convert(this::toGroupUserVO);
    }

    private GroupUserVO toGroupUserVO(UserGroupRole groupUser) {

        UserVO user = groupInnerConnectService.getUser(groupUser.getUserId());
        RoleVO role = groupInnerConnectService.getRole(groupUser.getRoleId());
        return new GroupUserVO(role, user);
    }

    @Override
    public void apply(GroupApplyDTO dto) {

        UserGroupRole existGroupUser = userGroupRoleRepository.getByUserIdAndGroupId(Loginer.userId(), dto.getToGroupId());
        Asserter.isNull(existGroupUser);
        GroupApplication groupApplication = groupApplicationRepository.getByFromIdAndToGroupId(Loginer.userId(), dto.getToGroupId());
        if (groupApplication == null) {
            groupApplication = new GroupApplication();
            groupApplication.setFromId(Loginer.userId());
            groupApplication.setToGroupId(dto.getToGroupId());
            groupApplication.setMemoName(dto.getMemoName());
            groupApplication.setCreateTime(LocalDateTime.now());
            groupApplication.setUpdateTime(LocalDateTime.now());
            groupApplication.setStatus("waiting_confirm");
        } else {
           groupApplication.setStatus("waiting_confirm");
            groupApplication.setUpdateTime(LocalDateTime.now());
        }
        groupApplicationRepository.save(groupApplication);
    }
    @Override
    public void confirm(GroupConfirmDTO dto) {

        GroupApplication groupApplication = groupApplicationRepository.getOne(dto.getGroupApplicationId());
        UserGroupRole existsUser = userGroupRoleRepository.getByUserIdAndGroupId(groupApplication.getFromId(), groupApplication.getToGroupId());
        Asserter.isNull(existsUser);
        groupApplication.setStatus("finish");
        groupApplication.setUpdateTime(LocalDateTime.now());
        groupApplicationRepository.save(groupApplication);
        UserGroupRole userGroupRole = new UserGroupRole();
        userGroupRole.setMemoName(dto.getMemoName());
        userGroupRole.setRoleId(dto.getRoleId());
        userGroupRole.setGroupId(groupApplication.getToGroupId());
        userGroupRole.setUserId(groupApplication.getFromId());
        userGroupRoleRepository.save(userGroupRole);

    }
}
