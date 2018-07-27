package com.mj.ocean.quotation.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/24 15:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuotationInfoVO {

    @ApiModelProperty("数据集")
    List<QuotationCostVO> quotationCosts;

    @ApiModelProperty("数据集")
    QuotationVO quotationVO;
}
