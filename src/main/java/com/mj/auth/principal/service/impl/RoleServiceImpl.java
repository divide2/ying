package com.mj.auth.principal.service.impl;

import com.mj.auth.acl.model.Acl;
import com.mj.auth.acl.repo.AclRepository;
import com.mj.auth.principal.dto.RoleAddAuthDTO;
import com.mj.auth.principal.dto.RoleAddUsersDTO;
import com.mj.auth.principal.dto.RoleQueryDTO;
import com.mj.auth.principal.model.QRole;
import com.mj.auth.principal.model.Role;
import com.mj.auth.principal.model.UserRole;
import com.mj.auth.principal.repo.RoleRepository;
import com.mj.auth.principal.repo.UserRoleRepository;
import com.mj.auth.principal.service.RoleService;
import com.mj.auth.res.model.Menu;
import com.mj.auth.res.service.MenuService;
import com.mj.core.basic.service.impl.SimpleBasicServiceImpl;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bvvy
 */
@Service
public class RoleServiceImpl extends SimpleBasicServiceImpl<Role, Integer, RoleRepository> implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final MenuService menuService;
    private final AclRepository aclRepository;

    public RoleServiceImpl(RoleRepository roleRepository,
                           UserRoleRepository userRoleRepository,
                           MenuService menuService,
                           AclRepository aclRepository) {
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.menuService = menuService;
        this.aclRepository = aclRepository;
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
        return roleRepository.findAll(predicate, pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUsers(RoleAddUsersDTO roleAddUsersDTO) {
        userRoleRepository.deleteByRoleId(roleAddUsersDTO.getRoleId());
        roleAddUsersDTO.getUserIds()
                .forEach(userId -> {
                    UserRole ur = new UserRole();
                    ur.setRoleId(roleAddUsersDTO.getRoleId());
                    ur.setUserId(userId);
                    userRoleRepository.save(ur);
                });
    }

    @Override
    public void addRoleAuth(RoleAddAuthDTO roleAddAuthDTO) {
        // 先删除 再添加  todo // 以后统一处理这种批量修改的情况
        aclRepository.deleteByPrincipalIdAndPrincipalTypeAndResType(roleAddAuthDTO.getRoleId(), Role.PRINCIPAL, Menu.RES_TYPE);
        roleAddAuthDTO.getResIds().
                forEach(resId -> {
                    Acl acl = new Acl();
                    acl.setPrincipalId(roleAddAuthDTO.getRoleId());
                    acl.setResId(resId);
                    acl.setPrincipalType(Role.PRINCIPAL);
                    acl.setResType(Menu.RES_TYPE);
                    acl.setAclStatus(1);
                    aclRepository.save(acl);
                });
    }
}
