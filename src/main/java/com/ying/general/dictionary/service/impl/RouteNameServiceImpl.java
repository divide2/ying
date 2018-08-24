package com.ying.general.dictionary.service.impl;

import com.ying.core.basic.service.BasicService;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author zejun
 * @date 2018/7/27 18:34
 */
@Service
public class RouteNameServiceImpl extends SimpleBasicServiceImpl<GeneralRouteJointName,Integer,RouteNameRepository>
        implements BasicService<GeneralRouteJointName, Integer> {
}
