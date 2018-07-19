package com.mj.ocean.quotation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zejun
 * @date 2018/7/19 09:34
 */
@Data
@ApiModel("修改常规报价")
public class QuotationUpdateDTO {

    @ApiModelProperty("报价id")
    public Integer id;

    @ApiModelProperty("船公司id")
    public Integer carrierId;

    @ApiModelProperty("航线id")
    public Integer routeId;

    @ApiModelProperty("起运港口id")
    public Integer portShipmentId;

    @ApiModelProperty("目的港口id")
    public Integer portDestinationId;

    @ApiModelProperty("截关时间")
    public LocalDateTime closingTime;

    @ApiModelProperty("开船时间")
    public LocalDateTime sailingTime;

    @ApiModelProperty("中转港口")
    public String transitPort;

    @ApiModelProperty("航程")
    public BigDecimal voyage;

    @ApiModelProperty("币种")
    public String currency;

    @ApiModelProperty("备注")
    public String remarks;

    @ApiModelProperty("有效期")
    @NotEmpty
    public String yermValidity;

    @ApiModelProperty("成本代码id")
    public Integer costId;

    @ApiModelProperty("是否发布")
    private Character publish;

    @ApiModelProperty("常规报价关联表数据集")
    List<QuotationCostUpdateDTO> quotationCostUpdateDTOList;
}
