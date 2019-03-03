package com.ying.product.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 * @date 2019/3/3
 */
@Data
public class WarehouseDTO {

    @NotEmpty
    private String teamId;
    @NotEmpty
    private String name;

    private String remarks;
}
