package com.mj.ocean.quotation.repo.custom;

import com.mj.ocean.quotation.dto.QuotationCallHistory;
import com.mj.ocean.quotation.dto.QuotationQueryDTO;
import com.mj.ocean.quotation.vo.QuotationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/19 11:14
 */
public interface QuotationRepositoryCustom {

    Page<QuotationVO> findAll(String costServiceCode, QuotationQueryDTO quotationQueryDTO, Pageable pageable);

    List<QuotationVO> callHistory(QuotationCallHistory quotationCallHistory);
}
