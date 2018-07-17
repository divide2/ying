package com.mj.ocean.portcombination.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/13 17:55
 */
@Data
@AllArgsConstructor
public class CombinationVO {

    @ApiModelProperty("港口组id")
    private Integer id;

    @ApiModelProperty("组合名称")
    private String combinationName;

    @ApiModelProperty("是否启用")
    private Character enabled;

    private String carrierCodes;

    private String portCodes;

    private String portENs;

    private String countryENs;
}
