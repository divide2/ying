package com.ying.product.vo;

import com.ying.product.dto.ImageDTO;
import com.ying.product.model.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/8/16
 */
@Data
public class ProductVO {

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
    List<ImageDTO> images;
    /**
     * 标签
     */
    @ApiModelProperty("标签 用，隔开")
    private String tags;
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
    @ApiModelProperty("启用 Y , N")
    private String enabled;
    /**
     * 备注 描述
     */
    @ApiModelProperty("备注描述")
    private String remarks;

    public static ProductVO of(Product product) {
        ProductVO vo = new ProductVO();
        vo.setId(product.getId());
        vo.setName(product.getName());
        vo.setTags(product.getTags());
        vo.setCdp(product.getCdp());
        vo.setLongitude(product.getLongitude());
        vo.setLatitude(product.getLatitude());
        vo.setEnabled(product.getEnabled());
        vo.setRemarks(product.getRemarks());
        return vo;
    }
}
