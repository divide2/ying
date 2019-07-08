package com.divide2.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 * @date 2019/4/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitDTO {
    private String id;
    private String name;
    private Integer rate;
}
