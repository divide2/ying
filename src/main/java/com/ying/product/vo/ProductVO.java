package com.ying.product.vo;

import com.ying.product.dto.ProductImageDTO;
import com.ying.product.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/8/16
 */
@Data
@ApiModel("产品列表数据")
public class ProductVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("创建人")
    private Integer fromId;

    @ApiModelProperty("创建人的名字")
    private String fromName;

    @ApiModelProperty("头像")
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

    @ApiModelProperty("创建时间")
    private LocalDateTime cdt;
    /**
     * 备注 描述
     */
    @ApiModelProperty("备注描述")
    private String remarks;

    @ApiModelProperty("star数量")
    private Long starCount;

    @ApiModelProperty("评论数量")
    private Long commentCount;


    @ApiModelProperty("查看数量")
    private Long readCount;


    public static ProductVO of(Product product) {
        ProductVO vo = new ProductVO();
        vo.setId(product.getId());
        vo.setName(product.getName());

        vo.setRemarks(product.getRemarks());
        vo.setFromId(product.getFromId());
        vo.setFromName(product.getFromName());
        vo.setCommentCount(10L);
        vo.setStarCount(199L);
        vo.setReadCount(299L);
        return vo;
    }


}
