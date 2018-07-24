package com.mj.ocean.surcharge.vo;

import com.mj.ocean.surcharge.model.Surcharge;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author bvvy
 * @date 2018/7/18
 */
@ApiModel("附加费查询")
@Data
public class SurchargeVO {
    /**
     * 船公司id
     */
    @ApiModelProperty("船公司id")
    private Integer carrierId;
    /**
     * 船公司id
     */
    @ApiModelProperty("船公司名字")
    private String carrierName;

    /**
     * 起运港口/组合 id
     */
    @ApiModelProperty("起运港口/组合 id")
    private Integer pomId;


    /**
     * 起运港口/组合 id
     */
    @ApiModelProperty("起运港口/组合名称")
    private String pomName;
    /**
     * 目的港口/组合
     */
    @ApiModelProperty(" 目的港口/组合")
    private Integer podId;

    /**
     * 目的港口/组合
     */
    @ApiModelProperty(" 目的港口/组合名称")
    private String podName;
    /**
     * 简称/编码
     */
    @ApiModelProperty("简称/编码")
    private String code;
    /**
     * 中文
     */
    @ApiModelProperty("中文")
    private String nameCn;
    /**
     * 英文
     */
    @ApiModelProperty("英文")
    private String nameEn;
    /**
     * 计费单位
     */
    @ApiModelProperty("计费单位")
    private String billingUnit;
    /**
     * 币种
     */
    @ApiModelProperty("币种")
    private String currency;
    /**
     * 金额
     */
    @ApiModelProperty("金额")
    private BigDecimal amt;

    /**
     * 支付方式
     */
    @ApiModelProperty("支付方式")
    private String payWay;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;
    /**
     * 启用
     */
    @ApiModelProperty("启用")
    private Character enabled;

    /**
     * @param surcharge surcharge
     * @return vo
     */
    public static SurchargeVO fromSurcharge(Surcharge surcharge) {
        SurchargeVO vo = new SurchargeVO();
        vo.setCarrierId(surcharge.getCarrierId());
        vo.setCarrierName(surcharge.getCarrierName());
        vo.setPodId(surcharge.getPodId());
        vo.setPodName(surcharge.getPodName());
        vo.setPomId(surcharge.getPomId());
        vo.setPomName(surcharge.getPomName());
        vo.setCode(surcharge.getCode());
        vo.setCurrency(surcharge.getCurrency());
        vo.setEnabled(surcharge.getEnabled());
        vo.setNameCn(surcharge.getNameCn());
        vo.setNameEn(surcharge.getNameEn());
        vo.setRemarks(surcharge.getRemarks());
        vo.setPayWay(surcharge.getPayWay());
        vo.setAmt(surcharge.getAmt());
        vo.setBillingUnit(surcharge.getBillingUnit());
        return vo;
    }
}
