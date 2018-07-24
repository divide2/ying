package com.mj.ocean.portcombination.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zejun
 * @date 2018/7/18 09:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CombinationAssociatedVO {
    @ApiModelProperty("港口组id")
    private Integer id;

    @ApiModelProperty("组合名称")
    private String combinationName;

    @ApiModelProperty("船公司id")
    private Integer carrierId;

    @ApiModelProperty("船公司简称")
    private String carrierCode;

    @ApiModelProperty("港口id")
    private Integer portId;

    @ApiModelProperty("港口简称")
    private String portCode;

    @ApiModelProperty("港口英文")
    private String portEN;

    @ApiModelProperty("国家英文")
    private String countryEN;
}
