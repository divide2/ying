package com.divide2.product.unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 * @date 2019/4/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockUnit {

    private String units;
    private Integer totalAmount;
}
