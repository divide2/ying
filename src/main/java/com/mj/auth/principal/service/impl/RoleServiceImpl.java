package com.mj.auth.principal.service.impl;

import com.mj.auth.principal.dto.RoleQueryDTO;
import com.mj.auth.principal.model.QRole;
import com.mj.auth.principal.model.Role;
import com.mj.auth.principal.repo.RoleRepository;
import com.mj.auth.principal.service.RoleService;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 */
@Service
public class RoleServiceImpl extends SimpleBasicServiceImpl<Role,Integer,RoleRepository> implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Page<Role> find(RoleQueryDTO query, Pageable pageable) {
        QRole role = QRole.role;
        Predicate predicate = Expressions.ONE.eq(Expressions.ONE);
        if (StringUtils.isNotEmpty(query.getName())) {
            predicate = role.name.like("%" + query.getName() + "%");
        }
        if (StringUtils.isNotEmpty(query.getCode())) {
            predicate = role.code.like("%" + query.getCode() + "%");
        }
        return roleRepository.findAll(predicate,pageable);
    }
}
