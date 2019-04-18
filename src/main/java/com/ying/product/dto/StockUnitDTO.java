package com.ying.product.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 * @date 2019/4/11
 */
@Data
public class StockUnitDTO {

    @NotEmpty
    private String id;
    @NotEmpty
    private String amount;


}
