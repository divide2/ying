package com.ying.general.dictionary.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.general.dictionary.model.GeneralRouteJointName;
import com.ying.general.dictionary.repo.RouteNameRepository;
import com.ying.general.dictionary.service.RouteNameService;
import org.springframework.stereotype.Service;

/**
 * @author zejun
 * @date 2018/7/27 18:34
 */
@Service
public class RouteNameServiceImpl extends SimpleBasicServiceImpl<GeneralRouteJointName,Integer,RouteNameRepository>
        implements RouteNameService {
}
