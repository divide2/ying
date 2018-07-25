package com.mj.ocean.quotation.repo.custom;

import com.mj.ocean.quotation.dto.QuotationCallHistory;
import com.mj.ocean.quotation.dto.QuotationQueryDTO;
import com.mj.ocean.quotation.model.Quotation;
import com.mj.ocean.quotation.vo.QuotationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/19 11:14
 */
public interface QuotationRepositoryCustom {

    /**
     * 分页查询报价数据
     * @param costServiceCode 报价类型
     * @param quotationQueryDTO 查询dto
     * @param pageable sql数据
     * @return Page<QuotationVO>
     */
    Page<QuotationVO> findAll(String costServiceCode, QuotationQueryDTO quotationQueryDTO, Pageable pageable);

    /**
     * 调用历史数据
     * @param quotationCallHistory 调用历史数据时查询条件
     * @return List<QuotationVO>
     */
    List<QuotationVO> callHistory(QuotationCallHistory quotationCallHistory,Quotation quotation);
}
