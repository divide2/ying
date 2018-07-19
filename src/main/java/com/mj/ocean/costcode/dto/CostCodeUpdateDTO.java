package com.mj.ocean.costcode.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zejun
 * @date 2018/7/17 11:08
 */
@Data
@ApiModel("修改成本代码")
public class CostCodeUpdateDTO {

    @ApiModelProperty("成本代码id")
    @NotNull
    private Integer id;

    @ApiModelProperty("成本代码")
    @NotEmpty
    private String code;

    @ApiModelProperty("修改人id")
    @NotEmpty
    private Integer updateUserid;

    @ApiModelProperty("修改人名称")
    @NotEmpty
    private String updateUsername;

    @ApiModelProperty("成本关联数据集")
    @NotEmpty
    private List<CostCodeAssociatedUpdateDTO> costCodeAssociatedUpdateDTOList;
}
