package com.mj.ocean.portcombination.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/18 09:33
 */
@Data
@AllArgsConstructor
public class CombinationAssociatedVO {
    @ApiModelProperty("港口组id")
    private Integer id;

    @ApiModelProperty("组合名称")
    private String combinationName;

    @ApiModelProperty("是否启用")
    private Character enabled;

    @ApiModelProperty("船公司id")
    private Integer carrierId;

    @ApiModelProperty("船公司简称")
    private String carrierCode;

    @ApiModelProperty("港口ids")
    private String portIds;

    @ApiModelProperty("港口简称集合")
    private String portCodes;

    @ApiModelProperty("港口英文集合")
    private String portENs;

    @ApiModelProperty("国家因为集合")
    private String countryENs;
}
