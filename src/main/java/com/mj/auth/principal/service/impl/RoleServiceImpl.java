package com.mj.auth.principal.service.impl;

import com.mj.auth.principal.model.Role;
import com.mj.auth.principal.repo.RoleRepository;
import com.mj.auth.principal.service.RoleService;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 */
@Service
public class RoleServiceImpl extends SimpleBasicServiceImpl<Role,Integer,RoleRepository> implements RoleService {



}
