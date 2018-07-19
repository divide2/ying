package com.mj.ocean.surcharge.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bvvy
 * @date 2018/7/18
 */
@Data
@ApiModel("附加费查询条件")
public class SurchargeQueryDTO {

    @ApiModelProperty("公司id")
    private Integer carrierId;

    @ApiModelProperty("起运港口/组合")
    private Integer pomId;

    @ApiModelProperty("目的港口/组合")
    private Integer podId;

}
