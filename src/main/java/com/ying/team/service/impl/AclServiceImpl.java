package com.ying.team.service.impl;

import com.ying.team.dto.RolePerAddDTO;

import com.ying.auth.repo.AclRepository;

import com.ying.team.model.Acl;
import com.ying.team.model.UserTeamRole;
import com.ying.team.repo.UserTeamRoleRepository;
import com.ying.team.service.AclService;
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
