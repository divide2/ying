package com.ying.auth.service.impl;

import com.ying.auth.model.*;
import com.ying.auth.repo.AclRepository;
import com.ying.auth.dto.RoleAddAuthDTO;
import com.ying.auth.dto.RoleAddUsersDTO;
import com.ying.auth.dto.RoleQueryDTO;
import com.ying.auth.model.UserGroupRole;
import com.ying.auth.repo.RoleRepository;
import com.ying.auth.repo.UserGroupRoleRepository;
import com.ying.auth.service.RoleService;
import com.ying.auth.service.MenuService;
import com.ying.auth.vo.RoleVO;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
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
    private final UserGroupRoleRepository userGroupRoleRepository;
    private final MenuService menuService;
    private final AclRepository aclRepository;

    public RoleServiceImpl(RoleRepository roleRepository,
                           UserGroupRoleRepository userGroupRoleRepository,
                           MenuService menuService,
                           AclRepository aclRepository) {
        this.roleRepository = roleRepository;
        this.userGroupRoleRepository = userGroupRoleRepository;
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
        userGroupRoleRepository.deleteByRoleId(roleAddUsersDTO.getRoleId());
        roleAddUsersDTO.getUserIds()
                .forEach(userId -> {
                    UserGroupRole ur = new UserGroupRole();
                    ur.setRoleId(roleAddUsersDTO.getRoleId());
                    ur.setUserId(userId);
                    userGroupRoleRepository.save(ur);
                });
    }

    @Override
    public void addRoleAuth(RoleAddAuthDTO roleAddAuthDTO) {
        // 先删除 再添加  todo // 以后统一处理这种批量修改的情况
        aclRepository.deleteByRoleId(roleAddAuthDTO.getRoleId());
        roleAddAuthDTO.getResIds().
                forEach(resId -> {
                    Acl acl = new Acl();
                    aclRepository.save(acl);
                });
    }

    @Override
    public RoleVO getVO(Integer roleId) {
        Role role = this.get(roleId);
        return
                RoleVO.builder().code(role.getCode()).name(role.getName()).id(role.getId()).build();
    }
}
