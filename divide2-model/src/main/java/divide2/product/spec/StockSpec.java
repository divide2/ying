package divide2.product.spec;

import divide2.product.unit.UnitAmount;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/4/18
 */
@Data
public class StockSpec {
    private String id;

    @ApiModelProperty("数量和单位")
    List<UnitAmount> unitAmounts;
}
