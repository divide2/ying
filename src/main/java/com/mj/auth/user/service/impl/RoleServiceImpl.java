package com.mj.auth.user.service.impl;

import com.mj.auth.user.model.Role;
import com.mj.auth.user.repo.RoleRepository;
import com.mj.auth.user.service.RoleService;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 */
@Service
public class RoleServiceImpl extends SimpleBasicServiceImpl<Role,Integer,RoleRepository> implements RoleService {

}
