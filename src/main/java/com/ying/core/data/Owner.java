package com.ying.core.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 * @date 2018/9/9
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    private Integer id;
    private String name;
    private String avatar;

}
