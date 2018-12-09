package com.ying.product.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 产品组成 还是由别人的产品合成的
 * @author bvvy
 * @date 2018/12/9
 */

@Data
@Entity
@Table(name="p_product_composite")
public class ProductComposite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    private Integer productId;

    /**
     * 组成的id 还是product
     */
    @ApiModelProperty("组成id 还是商品")
    @NotNull
    private Integer compositeId;

    @ApiModelProperty("数量")
    @NotNull
    private Integer amount;

}
