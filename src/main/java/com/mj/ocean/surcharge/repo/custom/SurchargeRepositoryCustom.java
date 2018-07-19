package com.mj.ocean.surcharge.repo.custom;

import com.mj.ocean.surcharge.dto.SurchargeQueryDTO;
import com.mj.ocean.surcharge.vo.SurchargeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/7/19
 */
public interface SurchargeRepositoryCustom {
    /**
     * 查 vo
     * @param surchargeQueryDTO query
     * @param pageable page
     * @return page (vo)
     */
    Page<SurchargeVO> findVO(SurchargeQueryDTO surchargeQueryDTO, Pageable pageable);

}
