package com.divide2.product.dto;

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

    private String productSpecId;
    private Integer amount;
}
