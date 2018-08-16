package com.ying.auth.acl.service.impl;

import com.ying.auth.acl.model.Acl;
import com.ying.auth.acl.repo.AclRepository;
import com.ying.auth.acl.service.AclService;
import com.ying.auth.principal.dto.RolePerAddDTO;
import com.ying.auth.principal.model.Role;
import com.ying.auth.res.model.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 */
@Service
public class AclServiceImpl implements AclService {
    private final AclRepository aclRepository;

    public AclServiceImpl(AclRepository aclRepository) {
        this.aclRepository = aclRepository;
    }

    @Override
    public void addRolePerm(RolePerAddDTO rolePerAddDTO) {
        Acl acl = Acl.builder()
                .aclStatus(1)
                .principalId(rolePerAddDTO.getRoleId())
                .principalType(Role.PRINCIPAL)
                .resId(rolePerAddDTO.getResId())
                .resType(Menu.RES_TYPE)
                .build();
        aclRepository.save(acl);
    }

    @Override
    public List<Integer> findMenuIdsByRole(Integer roleId) {
        return aclRepository.findMenuIdsByRole(roleId) ;
    }


}
