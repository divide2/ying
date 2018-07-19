package com.mj.ocean.quotation.service;

import com.mj.core.service.BasicService;
import com.mj.ocean.quotation.dto.QuotationAddDTO;
import com.mj.ocean.quotation.dto.QuotationCallHistory;
import com.mj.ocean.quotation.dto.QuotationQueryDTO;
import com.mj.ocean.quotation.dto.QuotationUpdateDTO;
import com.mj.ocean.quotation.model.Quotation;
import com.mj.ocean.quotation.vo.QuotationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/17 18:24
 */
public interface QuotationService extends BasicService<Quotation,Integer> {

    void add(QuotationAddDTO quotationAddDTO);

    QuotationVO getOne(Integer id);

    void update(QuotationUpdateDTO quotationUpdateDTO);

    void addBacth(List<QuotationAddDTO> quotationAddDTOS);

    Page<QuotationVO> find(String costServiceCode,QuotationQueryDTO quotationQueryDTO, Pageable pageable);

    List<QuotationVO> callHistory(QuotationCallHistory quotationCallHistory);
}
