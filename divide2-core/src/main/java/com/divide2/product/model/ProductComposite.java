package com.divide2.product.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @GeneratedValue(generator = "custom-uuid")
    @GenericGenerator(
            name = "custom-uuid",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private String id;

    @JsonIgnore
    private String productId;

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
