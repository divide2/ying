package com.mj.ocean.portcombination.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/16 09:01
 */
@Data
@ApiModel("港口组合查询")
public class CombinationQueryDTO {
    @ApiModelProperty("船公司简称")
    private String carrierCode;

    @ApiModelProperty("组合名称")
    private String combinationName;


}
