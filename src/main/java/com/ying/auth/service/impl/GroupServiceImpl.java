package com.ying.auth.service.impl;

import com.ying.auth.dto.GroupAddDTO;
import com.ying.auth.model.Group;
import com.ying.auth.model.UserGroupRole;
import com.ying.auth.repo.GroupRepository;
import com.ying.auth.repo.UserGroupRoleRepository;
import com.ying.auth.service.GroupService;
import com.ying.auth.vo.GroupUserVO;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.er.Asserter;
import com.ying.core.er.Loginer;
import com.ying.core.exception.AlreadyExistsException;
import com.ying.core.exception.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/13
 */
@Service
public class GroupServiceImpl extends SimpleBasicServiceImpl<Group,String, GroupRepository> implements GroupService {
    private final UserGroupRoleRepository userGroupRoleRepository;
    private final GroupRepository groupRepository;

    public GroupServiceImpl(UserGroupRoleRepository userGroupRoleRepository, GroupRepository groupRepository) {
        this.userGroupRoleRepository = userGroupRoleRepository;
        this.groupRepository = groupRepository;
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
        return null;
    }
}
