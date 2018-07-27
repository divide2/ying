package com.mj.ocean.portcombination.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zejun
 * @date 2018/7/27 16:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CombinationAllVO {
    @ApiModelProperty("港口组id")
    private Integer id;

    @ApiModelProperty("组合名称")
    private String combinationName;
}
