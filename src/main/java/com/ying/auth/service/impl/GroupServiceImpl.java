package com.ying.auth.service.impl;

import com.ying.auth.dto.GroupAddDTO;
import com.ying.auth.model.Group;
import com.ying.auth.model.UserGroupRole;
import com.ying.auth.repo.GroupRepository;
import com.ying.auth.repo.UserGroupRoleRepository;
import com.ying.auth.service.GroupService;
import com.ying.auth.service.GroupInnerConnectService;
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

    public GroupServiceImpl(UserGroupRoleRepository userGroupRoleRepository, GroupRepository groupRepository, GroupInnerConnectService groupInnerConnectService) {
        this.userGroupRoleRepository = userGroupRoleRepository;
        this.groupRepository = groupRepository;
        this.groupInnerConnectService = groupInnerConnectService;
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
    public GroupVO getVO(String groupId) {
        Group group = this.get(groupId);
        return new GroupVO(
                group.getId(),
                group.getName(),
                group.getImage()
        );
    }

}
