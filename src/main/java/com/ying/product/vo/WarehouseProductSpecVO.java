package com.ying.product.vo;

import com.ying.product.model.WarehouseProductSpec;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 * @date 2018/12/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseProductSpecVO {
    private Integer productSpecId;
    private String productSpecName;
    private Integer amount;

    public static WarehouseProductSpecVO from(WarehouseProductSpec source) {

        return new WarehouseProductSpecVO(source.getProductSpecId(),
                source.getProductSpecName(),
                source.getAmount());
    }
}
