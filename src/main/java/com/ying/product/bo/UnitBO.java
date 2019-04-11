package com.ying.product.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 * @date 2019/4/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitBO {
    private String id;
    private String name;
    private Integer rate;
    private String child;
}
