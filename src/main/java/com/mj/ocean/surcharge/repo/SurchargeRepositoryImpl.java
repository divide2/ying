package com.mj.ocean.surcharge.repo;

import com.mj.ocean.surcharge.dto.SurchargeQueryDTO;
import com.mj.ocean.surcharge.repo.custom.SurchargeRepositoryCustom;
import com.mj.ocean.surcharge.vo.SurchargeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

/**
 * @author bvvy
 * @date 2018/7/19
 */
public class SurchargeRepositoryImpl implements SurchargeRepositoryCustom {

    private final EntityManager entityManager;

    public SurchargeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<SurchargeVO> findVO(SurchargeQueryDTO surchargeQueryDTO, Pageable pageable) {
        return null;
    }
}
