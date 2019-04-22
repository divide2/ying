package com.divide2.product.spec;

import com.divide2.product.unit.OrderUnit;
import com.divide2.product.unit.StockUnit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/4/18
 */
@Data
public class StockSpec {
    private String id;

    @ApiModelProperty("数量和单位")
    List<StockUnit> units;
}
