package com.ying.team.service.impl;

import com.ying.team.model.Attention;
import com.ying.team.repo.AttentionRepository;
import com.ying.team.service.AttentionService;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2019/1/23
 */
@Service
public class AttentionServiceImpl extends SimpleBasicServiceImpl<Attention, String, AttentionRepository> implements AttentionService {

}
