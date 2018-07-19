package com.mj.ocean.quotation.service.impl;

import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.ocean.quotation.model.QuotationCost;
import com.mj.ocean.quotation.repo.QuotationCostRepository;
import com.mj.ocean.quotation.service.QuotationCostService;
import org.springframework.stereotype.Service;

/**
 * @author zejun
 * @date 2018/7/17 18:27
 */
@Service
public class QuotationCostServiceImpl extends SimpleBasicServiceImpl<QuotationCost,Integer,QuotationCostRepository>
        implements QuotationCostService {

    private final QuotationCostRepository quotationCostRepository;

    public QuotationCostServiceImpl(QuotationCostRepository quotationCostRepository) {
        this.quotationCostRepository = quotationCostRepository;
    }
}
