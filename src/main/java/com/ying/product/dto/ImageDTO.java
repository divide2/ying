package com.ying.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bvvy
 * @date 2018/8/18
 */
@Data
@ApiModel("图片")
public class ImageDTO {

    @ApiModelProperty("图片地址")
    private String url;

    @ApiModelProperty("是否是主图")
    private Boolean main;
}
