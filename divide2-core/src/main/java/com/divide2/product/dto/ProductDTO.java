package com.divide2.product.dto;

import com.divide2.product.spec.IProductSpec;
import com.divide2.product.unit.IProductUnit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
    @NotEmpty
    private List<ProductUnitDTO> units;

    @ApiModelProperty("规格")
    @NotEmpty
    private List<ProductSpecDTO> specs;

    @Data
    public static class ProductUnitDTO implements IProductUnit {
        private String name;
        private Integer rate;

        @Override
        @ApiModelProperty(hidden = true)
        public String getId() {
            return null;
        }
    }
    @Data
    public static class ProductSpecDTO implements IProductSpec {
        @NotEmpty
        private String name;

        @NotNull
        private BigDecimal price;

        private String [] image;

        @Override
        @ApiModelProperty(hidden = true)
        public String getId() {
            return null;
        }
    }
}

