package com.ying.product.product.vo;

import com.ying.product.product.dto.ProductImageDTO;
import com.ying.product.product.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author bvvy
 * @date 2018/8/16
 */
@Data
@ApiModel("产品列表数据")
public class ProductVO {

    @ApiModelProperty("id")
    private Integer id;

    private Integer fromId;

    private String fromName;

    private String fromAvatar;
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
        vo.setRemarks(product.getRemarks());
        vo.setFromId(product.getFromId());
        vo.setFromName(product.getFromName());
        vo.setFromAvatar(product.getFromAvatar());
        vo.setImages(product.getImages().stream().map(ProductImageDTO::of).collect(toList()));
        return vo;
    }


}
