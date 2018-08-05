package com.mj.ocean.quotation.service;

import com.mj.core.service.BasicService;
import com.mj.ocean.quotation.dto.QuotationAddDTO;
import com.mj.ocean.quotation.dto.QuotationCallHistory;
import com.mj.ocean.quotation.dto.QuotationQueryDTO;
import com.mj.ocean.quotation.dto.QuotationUpdateDTO;
import com.mj.ocean.quotation.model.Quotation;
import com.mj.ocean.quotation.vo.QuotationInfoVO;
import com.mj.ocean.quotation.vo.QuotationOneVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/17 18:24
 */
public interface QuotationService extends BasicService<Quotation,Integer> {

    /**
     * 新增报价数据
     * @param quotationAddDTO 新增dto
     */
    void add(QuotationAddDTO quotationAddDTO);

    /**
     * 根据id获取单条数据
     * @param id 报价id
     * @return QuotationVO
     */
    QuotationOneVO getOne(Integer id);

    /**
     * 修改报价数据
     * @param quotationUpdateDTO 修改dto
     */
    void update(QuotationUpdateDTO quotationUpdateDTO);

    /**
     * 批量增加报价数据
     * @param quotationAddDTOS 报价数据集
     */
    void addBacth(List<QuotationAddDTO> quotationAddDTOS);

    /**
     * 分页查询报价数据
     * @param costServiceCode 报价类型
     * @param quotationQueryDTO 查询dto
     * @param pageable sql数据
     * @return Page<QuotationInfoVO>
     */
    Page<QuotationOneVO> find(String costServiceCode,QuotationQueryDTO quotationQueryDTO, Pageable pageable);

    /**
     * 调用历史数据
     * @param quotationCallHistory 调用历史数据时查询条件
     * @return List<QuotationInfoVO>
     */
    List<QuotationInfoVO> callHistory(QuotationCallHistory quotationCallHistory);

    /**
     * 切换状态
     * @param id id
     */
    void toggleEnable(Integer id);
}
