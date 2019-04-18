package com.divide2.product.dto;

import com.divide2.product.model.ProductSpec;
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
@ApiModel("产品修改数据")
public class ProductUpdateDTO {

    private String id;

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
