package com.mj.general.carrier.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/9 15:21
 */
@Data
@ApiModel("船公司查询")
public class CarrierQueryDTO {

    @ApiModelProperty("船公司简称/中文名/英文名")
    private String keyName;

}
