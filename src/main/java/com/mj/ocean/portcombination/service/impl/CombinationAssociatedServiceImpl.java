package com.mj.ocean.portcombination.service.impl;

import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.ocean.portcombination.model.PortCombinationAssociated;
import com.mj.ocean.portcombination.repo.CombinationAssociatedRepository;
import com.mj.ocean.portcombination.service.CombinationAssociatedService;
import org.springframework.stereotype.Service;

/**
 * @author zejun
 * @date 2018/7/16 21:18
 */
@Service
public class CombinationAssociatedServiceImpl extends SimpleBasicServiceImpl<PortCombinationAssociated,Integer,CombinationAssociatedRepository>
        implements CombinationAssociatedService {


}
