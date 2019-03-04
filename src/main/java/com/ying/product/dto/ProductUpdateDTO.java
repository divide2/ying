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
@ApiModel("产品修改数据")
public class ProductUpdateDTO {

    private String id;

    @ApiModelProperty("名字")
    @NotEmpty
    private String name;

    private Integer initAmount;

    @ApiModelProperty("图")
    private String [] image;

    private String remarks;

    @ApiModelProperty("规格")
    @NotEmpty
    private List<ProductSpec> specs;


}
