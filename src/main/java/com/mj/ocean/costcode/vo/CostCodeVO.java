package com.mj.ocean.costcode.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zejun
 * @date 2018/7/17 11:34
 */
@Data
@Builder
public class CostCodeVO {

    @ApiModelProperty("成本id")
    private Integer id;

    @ApiModelProperty("成本代码")
    private String code;

    @ApiModelProperty("最后修改者")
    private String lastUpdateName;

    @ApiModelProperty("最后修改时间")
    private LocalDateTime lastUpdateDate;

    @ApiModelProperty("状态")
    private Character enabled;

    @ApiModelProperty("成本关联数据集")
    private List<CostCodeAssociatedVO> costCodeAssociatedVOs;
}
