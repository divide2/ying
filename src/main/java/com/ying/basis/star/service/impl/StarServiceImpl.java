package com.ying.basis.star.service.impl;

import com.ying.basis.star.dto.StarAddDTO;
import com.ying.basis.star.model.Star;
import com.ying.basis.star.repository.StarRepository;
import com.ying.basis.star.service.StarService;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/9/10
 */
@Service
public class StarServiceImpl extends SimpleBasicServiceImpl<Star,Integer,StarRepository> implements StarService {

    private final StarRepository starRepository;

    public StarServiceImpl(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    @Override
    public void add(StarAddDTO dto) {
        Star star = new Star();
        star.setCdt(LocalDateTime.now());
        //todo add login user
        star.setToId(dto.getToId());
        star.setToName(dto.getToName());
        star.setType(dto.getType());
        starRepository.save(star);
    }



}
