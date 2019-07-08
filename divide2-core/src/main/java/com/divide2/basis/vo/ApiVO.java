package com.divide2.basis.vo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * @author bvvy
 */
@ApiModel
@Data
@Builder
public class ApiVO {
    private Integer id;
    private String name;
    private String path;
    private String method;

}
