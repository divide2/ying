package com.mj.general.dictionary.service.impl;

import com.mj.core.basic.service.impl.SimpleBasicServiceImpl;
import com.mj.general.dictionary.model.GeneralRouteJointName;
import com.mj.general.dictionary.repo.RouteNameRepository;
import com.mj.general.dictionary.service.RouteNameService;
import org.springframework.stereotype.Service;

/**
 * @author zejun
 * @date 2018/7/27 18:34
 */
@Service
public class RouteNameServiceImpl extends SimpleBasicServiceImpl<GeneralRouteJointName,Integer,RouteNameRepository>
        implements RouteNameService {
}
