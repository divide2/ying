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

    private Integer carrierId;

    private String carrierCode;

    private String portIds;

    private String portCodes;

    private String portENs;

    private String countryENs;
}
