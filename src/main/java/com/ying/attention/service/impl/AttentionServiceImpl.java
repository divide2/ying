package com.ying.attention.service.impl;

import com.ying.attention.model.Attention;
import com.ying.attention.repo.AttentionRepository;
import com.ying.attention.service.AttentionService;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2019/1/23
 */
@Service
public class AttentionServiceImpl extends SimpleBasicServiceImpl<Attention, Integer, AttentionRepository> implements AttentionService {

}
