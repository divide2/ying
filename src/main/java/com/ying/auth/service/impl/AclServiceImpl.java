package com.ying.auth.service.impl;

import com.ying.auth.model.Acl;
import com.ying.auth.model.UserTeamRole;
import com.ying.auth.repo.AclRepository;
import com.ying.auth.repo.UserTeamRoleRepository;
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
    private final UserTeamRoleRepository userTeamRoleRepository;

    public AclServiceImpl(AclRepository aclRepository,
                          UserTeamRoleRepository userTeamRoleRepository) {
        this.aclRepository = aclRepository;
        this.userTeamRoleRepository = userTeamRoleRepository;
    }

    @Override
    public void addRolePerm(RolePerAddDTO rolePerAddDTO) {
        Acl acl = new Acl();
        aclRepository.save(acl);
    }


    @Override
    public List<String> listAuthorities(Integer userId, String teamId) {
        UserTeamRole userTeamRole = userTeamRoleRepository.getByUserIdAndTeamId(userId, teamId);
        return aclRepository.findAuthorities(userTeamRole.getRoleId());
    }
}
