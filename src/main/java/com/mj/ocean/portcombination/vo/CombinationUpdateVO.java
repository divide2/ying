package com.mj.ocean.portcombination.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/7/25
 */
@ApiModel("单个组合信息")
@Data
public class CombinationUpdateVO {

    @ApiModelProperty("组合id")
    private Integer combinationId;
    @ApiModelProperty("组合名称")
    private String combinationName;
    @ApiModelProperty("公司id")
    private Integer carrierId;
    @ApiModelProperty("港口ids")
    private List<Integer> portIds;
}
