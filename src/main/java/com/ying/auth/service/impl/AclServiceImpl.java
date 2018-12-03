package com.ying.auth.service.impl;

import com.ying.auth.model.Acl;
import com.ying.auth.repo.AclRepository;
import com.ying.auth.service.AclService;
import com.ying.auth.dto.RolePerAddDTO;
import com.ying.auth.model.Role;
import com.ying.auth.model.Menu;
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
