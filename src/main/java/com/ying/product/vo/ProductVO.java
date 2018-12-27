package com.ying.product.vo;

import com.ying.product.model.Product;
import com.ying.product.model.ProductSpec;
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

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图片")
    private String image;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    /**
     * 备注 描述
     */
    @ApiModelProperty("备注描述")
    private String remarks;

    @ApiModelProperty("产品规格")
    private List<ProductSpec> specs;

    public static ProductVO of(Product product) {
        ProductVO vo = new ProductVO();
        vo.setId(product.getId());
        vo.setImage(product.getImage());
        vo.setName(product.getName());
        vo.setRemarks(product.getRemarks());
        return vo;
    }


}
