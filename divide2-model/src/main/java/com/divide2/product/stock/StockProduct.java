package com.divide2.product.stock;

import com.divide2.product.spec.StockSpec;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author bvvy
 * @date 2019/4/18
 */
@Data
public class StockProduct {
    @NotEmpty
    private String productId;

    @ApiModelProperty(value = "出入库方式,系统生成",hidden = true)
    private String type;

    private String remarks;

    @ApiModelProperty("产品规格")
    @NotEmpty
    private List<StockSpec> specs;
}
