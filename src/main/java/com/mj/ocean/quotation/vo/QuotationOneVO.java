package com.mj.ocean.quotation.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zejun
 * @date 2018/7/18 18:29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuotationOneVO {

    @ApiModelProperty("常规报价id")
    private Integer id;

    @ApiModelProperty("船公司id")
    private Integer carrierId;

    @ApiModelProperty("船公司code")
    private String carrierCode;

    @ApiModelProperty("航线id")
    private Integer routeId;

    @ApiModelProperty("航线代码")
    private String routeCode;

    @ApiModelProperty("起运港口id")
    private Integer portShipmentId;

    @ApiModelProperty("起运港口")
    private String portShipment;

    @ApiModelProperty("目的港口id")
    private Integer portDestinationId;

    @ApiModelProperty("目的港口")
    private String portDestination;

    @ApiModelProperty("截关时间")
    private String etc;

    @ApiModelProperty("开船时间")
    private String etd;

    @ApiModelProperty("中转港口")
    private String transitPort;

    @ApiModelProperty("航程")
    private BigDecimal tt;

    @ApiModelProperty("币种")
    private String currency;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("有效期")
    private String yermValidity;

    @ApiModelProperty("成本代码id")
    private Integer costId;

    @ApiModelProperty("成本代码code")
    private String costCode;

    @ApiModelProperty("报价类别")
    private String costServiceCode;

    private List<QuotationCostVO> quotationCosts;
}
