package com.mj.general.charge.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/10 09:38
 */
@Data
@ApiModel("费用项目查询")
public class ChargeQueryDTO {
    @ApiModelProperty("费用简称/中文名/英文名")
    private String keyName;
}
