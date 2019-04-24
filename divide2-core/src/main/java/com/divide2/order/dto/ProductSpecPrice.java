package com.divide2.order.dto;

import com.divide2.product.dto.StockUnitDTO;
import com.divide2.product.unit.UnitAmount;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Data
public class ProductSpecPrice {

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private String orderId;

    private String productId;

    @ApiModelProperty(value = "产品属性关联表id")
    private String productSpecId;

    @ApiModelProperty("单位数量")
    @NotEmpty
    private List<UnitAmount> units;

    private BigDecimal price;

}
