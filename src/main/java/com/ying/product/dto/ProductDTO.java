package com.ying.product.dto;

import com.ying.product.model.ProductSpec;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/8/16
 */
@Data
@ApiModel("产品添加数据")
public class ProductDTO {

    @NotEmpty
    private String teamId;

    @ApiModelProperty("名字")
    @NotEmpty
    private String name;

    private String categoryId;

    @ApiModelProperty("图")
    private String [] image;

    private String remarks;

    @ApiModelProperty("单位")
    private List<UnitDTO> units;

    @ApiModelProperty("规格")
    @NotEmpty
    private List<ProductSpec> specs;

}
