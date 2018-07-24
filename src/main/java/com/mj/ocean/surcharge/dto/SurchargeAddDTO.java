package com.mj.ocean.surcharge.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/7/18
 */
@ApiModel("附加费添加")
@Data
public class SurchargeAddDTO {
    /**
     * 船公司id
     */
    @ApiModelProperty("船公司id")
    @NotNull
    private Integer carrierId;
    /**
     * 起运港口/组合 id
     */
        @ApiModelProperty("起运港口/组合 id")
    @NotNull
    private Integer pomId;
    /**
     * 目的港口/组合
     */
    @ApiModelProperty(" 目的港口/组合")
    @NotNull
    private Integer podId;

    @NotEmpty
    @ApiModelProperty("费用信息")
    List<SurchargeKeeper> surcharges;

    @Data
    public static class SurchargeKeeper {
        /**
         * 简称/编码
         */
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
    }

}
