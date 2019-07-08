package com.divide2.product.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 * @date 2019/3/3
 */
@Data
public class WarehouseUpdateDTO {

    private String id;
    @NotEmpty
    private String name;

    private String remarks;
}
