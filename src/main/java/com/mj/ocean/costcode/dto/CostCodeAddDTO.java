package com.mj.ocean.costcode.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author zejun
 * @date 2018/7/17 10:24
 */
@Data
@ApiModel("新增成本代码")
public class CostCodeAddDTO {

    @ApiModelProperty("成本代码")
    @NotEmpty
    private String code;

    @ApiModelProperty("创建人id")
    @NotEmpty
    private Integer createdUserid;

    @ApiModelProperty("创建人名称")
    @NotEmpty
    private String createdUsername;

    @ApiModelProperty("成本关联数据集")
    @NotEmpty
    private List<CostCodeAssociatedAddDTO> costCodeAssociateds;
}
