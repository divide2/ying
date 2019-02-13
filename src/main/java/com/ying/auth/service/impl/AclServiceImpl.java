package com.ying.auth.service.impl;

import com.ying.auth.model.Acl;
import com.ying.auth.model.UserGroupRole;
import com.ying.auth.repo.AclRepository;
import com.ying.auth.repo.UserGroupRoleRepository;
import com.ying.auth.service.AclService;
import com.ying.auth.dto.RolePerAddDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 */
@Service
public class AclServiceImpl implements AclService {
    private final AclRepository aclRepository;
    private final UserGroupRoleRepository userGroupRoleRepository;

    public AclServiceImpl(AclRepository aclRepository,
                          UserGroupRoleRepository userGroupRoleRepository) {
        this.aclRepository = aclRepository;
        this.userGroupRoleRepository = userGroupRoleRepository;
    }

    @Override
    public void addRolePerm(RolePerAddDTO rolePerAddDTO) {
        Acl acl = new Acl();
        aclRepository.save(acl);
    }


    @Override
    public List<String> listAuthorities(Integer userId, String groupId) {
        UserGroupRole userGroupRole = userGroupRoleRepository.getByUserIdAndGroupId(userId, groupId);
        return aclRepository.findAuthorities(userGroupRole.getRoleId());
    }
}
