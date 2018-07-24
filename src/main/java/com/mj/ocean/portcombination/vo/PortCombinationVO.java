package com.mj.ocean.portcombination.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @author zejun
 * @date 2018/7/24 11:24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortCombinationVO {
    @ApiModelProperty("港口组id")
    private Integer id;

    @ApiModelProperty("组合名称")
    private String combinationName;

    @ApiModelProperty("关联表数据集")
    private List<CombinationAssociatedVO> combinationAssociatedVOs;


}
