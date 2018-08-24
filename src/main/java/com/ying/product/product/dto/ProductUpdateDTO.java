package com.ying.product.product.dto;

import com.ying.product.product.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/8/16
 */
@Data
@ApiModel("产品修改数据")
public class ProductUpdateDTO {

    @ApiModelProperty("id")
    private Integer id;
    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;
    /**
     * 图片
     */
    @ApiModelProperty("图片")
    List<ProductImageDTO> images;
    /**
     * 标签
     */
    @ApiModelProperty("标签 用，隔开")
    private String[] tags;
    /**
     * 创建地点
     */
    @ApiModelProperty("创建地点")
    private String cdp;
    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private BigDecimal longitude;
    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private BigDecimal latitude;
    /**
     * 启用
     */
    @ApiModelProperty
    private Boolean enabled;
    /**
     * 备注 描述
     */
    @ApiModelProperty("备注描述")
    private String remarks;

    public Product toProduct() {
        return Product.builder()
                .id(this.getId())
                .tags(this.getTags())
                .cdp(this.getCdp())
                .longitude(this.getLongitude())
                .latitude(this.getLatitude())
                .enabled(this.getEnabled())
                .remarks(this.getRemarks())
                .build();
    }

}
