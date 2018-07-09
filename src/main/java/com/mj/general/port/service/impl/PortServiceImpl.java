package com.mj.general.port.service.impl;

import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.general.port.model.Port;
import com.mj.general.port.repo.PortRepository;
import com.mj.general.port.service.PortService;
import org.springframework.stereotype.Service;

/**
 * @auther: zejun
 * @date: 2018/7/9 17:49
 */
@Service
public class PortServiceImpl extends SimpleBasicServiceImpl<Port,Integer,PortRepository> implements PortService {

}
