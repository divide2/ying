package com.mj.auth.res.service.impl;

import com.mj.auth.res.model.Menu;
import com.mj.auth.res.repo.MenuRepository;
import com.mj.auth.res.service.MenuService;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 */
@Service
public class MenuServiceImpl extends SimpleBasicServiceImpl<Menu,Integer,MenuRepository> implements MenuService {

}
