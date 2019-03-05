package com.ying.product.vo;

import com.ying.product.model.SpecStock;
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
    private String id;
    private String name;
    private Integer amount;

    public static WarehouseProductSpecVO from(SpecStock source) {

        return new WarehouseProductSpecVO(source.getProductSpecId(),
                source.getProductSpecName(),
                source.getAmount());
    }
}
