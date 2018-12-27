package com.ying.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 * @date 2018/12/9
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSpecStock {

    private Integer productSpecId;
    private Integer amount;
}
