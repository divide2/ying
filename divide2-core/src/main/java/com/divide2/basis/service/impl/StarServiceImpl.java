package com.divide2.basis.service.impl;

import com.divide2.basis.dto.StarAddDTO;
import com.divide2.basis.model.Star;
import com.divide2.basis.repo.StarRepository;
import com.divide2.basis.service.StarService;
import com.divide2.core.basic.service.impl.SimpleBasicServiceImpl;
import com.divide2.core.er.Loginer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Star> findByUser(Pageable pageable) {
        return starRepository.findByFromId(Loginer.userId(), pageable);
    }
}
