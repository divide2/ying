package com.mj.ocean.quotation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zejun
 * @date 2018/7/17 18:49
 */
@Data
@ApiModel("新增常规报价")
public class QuotationAddDTO {

    @ApiModelProperty("船公司id")
    @NotNull
    public Integer carrierId;

    @ApiModelProperty("航线id")
    @NotNull
    public Integer routeId;

    @ApiModelProperty("起运港口id")
    @NotNull
    public Integer portShipmentId;

    @ApiModelProperty("目的港口id")
    @NotNull
    public Integer portDestinationId;

    @ApiModelProperty("截关时间")
    public LocalDateTime etc;

    @ApiModelProperty("开船时间")
    @NotNull
    public LocalDateTime etd;

    @ApiModelProperty("中转港口")
    public String transitPort;

    @ApiModelProperty("航程")
    @NotNull
    public BigDecimal tt;

    @ApiModelProperty("币种")
    @NotEmpty
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

    @ApiModelProperty("报价类别")
    public String costServiceCode;

    @ApiModelProperty("常规报价关联表数据集")
    List<QuotationCostAddDTO> quotationCostAdds;
}
