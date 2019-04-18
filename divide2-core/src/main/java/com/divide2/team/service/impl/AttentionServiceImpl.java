package com.divide2.team.service.impl;

import com.divide2.team.model.Attention;
import com.divide2.team.repo.AttentionRepository;
import com.divide2.team.service.AttentionService;
import com.divide2.core.basic.service.impl.SimpleBasicServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2019/1/23
 */
@Service
public class AttentionServiceImpl extends SimpleBasicServiceImpl<Attention, String, AttentionRepository> implements AttentionService {

}
