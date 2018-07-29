package com.mj.ocean.surcharge.keeper;

import com.mj.ocean.surcharge.model.Surcharge;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author bvvy
 * @date 2018/7/28
 */

@Data
public class SurchargeKeeper {

    @ApiModelProperty("简称/编码")
    @NotEmpty
    private String code;
    /**
     * 中文
     */
    @ApiModelProperty("中文")
    @NotEmpty
    private String nameCn;
    /**
     * 英文
     */
    @ApiModelProperty("英文")
    @NotEmpty
    private String nameEn;
    /**
     * 计费单位
     */
    @ApiModelProperty("计费单位")
    @NotEmpty
    private String billingUnit;
    /**
     * 币种
     */
    @ApiModelProperty("币种")
    @NotEmpty
    private String currency;
    /**
     * 金额
     */
    @ApiModelProperty("金额")
    @NotNull
    private BigDecimal amt;

    /**
     * 支付方式
     */
    @ApiModelProperty("支付方式")
    @NotEmpty
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
    @NotEmpty
    private Character enabled;

    /**
     * @param surcharge surcharge
     * @return vo
     */
    public static SurchargeKeeper ofSurcharge(Surcharge surcharge) {
        SurchargeKeeper keeper = new SurchargeKeeper();
        keeper.setCode(surcharge.getCode());
        keeper.setNameCn(surcharge.getNameCn());
        keeper.setNameEn(surcharge.getNameEn());
        keeper.setBillingUnit(surcharge.getBillingUnit());
        keeper.setCurrency(surcharge.getCurrency());
        keeper.setAmt(surcharge.getAmt());
        keeper.setPayWay(surcharge.getPayWay());
        keeper.setRemarks(surcharge.getRemarks());
        keeper.setEnabled(surcharge.getEnabled());
        return keeper;
    }
}
