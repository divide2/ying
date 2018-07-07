package com.mj.auth.res.service.impl;

import com.mj.auth.res.model.Oper;
import com.mj.auth.res.repo.OperRepository;
import com.mj.auth.res.service.OperService;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 */
@Service
public class OperServiceImpl extends SimpleBasicServiceImpl<Oper, Integer, OperRepository> implements OperService {

    private final OperRepository operRepository;

    public OperServiceImpl(OperRepository operRepository) {
        this.operRepository = operRepository;
    }

    @Override
    public Oper add(Oper oper) {
        Oper tOper = operRepository.findByPathAndMethod(oper.getPath(),oper.getMethod());
        if(tOper == null) {
            tOper = new Oper();
        }
        tOper.setPath(oper.getPath());
        tOper.setMethod(oper.getMethod());
        tOper.setName(oper.getName());
        return operRepository.save(tOper);
    }
}
