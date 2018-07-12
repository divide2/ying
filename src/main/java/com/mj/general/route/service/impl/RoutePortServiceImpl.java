package com.mj.general.route.service.impl;

import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.general.route.model.RoutePort;
import com.mj.general.route.repo.RoutePortRepository;
import com.mj.general.route.service.RoutePortService;
import org.springframework.stereotype.Service;

/**
 * @author zejun
 * @date 2018/7/10 15:20
 */
@Service
public class RoutePortServiceImpl extends SimpleBasicServiceImpl<RoutePort,Integer,RoutePortRepository> implements RoutePortService {
}
