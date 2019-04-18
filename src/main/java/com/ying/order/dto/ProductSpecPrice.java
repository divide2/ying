package com.ying.order.dto;

import com.ying.product.dto.StockUnitDTO;
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
    private List<StockUnitDTO> units;

    private BigDecimal price;

}
