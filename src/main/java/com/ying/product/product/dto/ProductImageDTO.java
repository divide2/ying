package com.ying.product.product.dto;

import com.ying.product.product.model.ProductImage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bvvy
 * @date 2018/8/18
 */
@Data
@ApiModel("图片")
public class ProductImageDTO {

    @ApiModelProperty("图片地址")
    private String url;

    @ApiModelProperty("是否是主图")
    private Boolean main;

    @ApiModelProperty("排序号")
    private Integer orderNum;

    public ProductImage to() {
        ProductImage pi = new ProductImage();
        pi.setMain(main);
        pi.setOrderNum(orderNum);
        pi.setUrl(url);
        return pi;
    }

    public static ProductImageDTO of(ProductImage pi) {

        ProductImageDTO dto = new ProductImageDTO();
        dto.setMain(pi.getMain());
        dto.setOrderNum(pi.getOrderNum());
        dto.setUrl(pi.getUrl());
        return dto;
    }
}
