package divide2.product.stock;

import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/4/18
 */
@Data
public class OutStock {
    private String teamId;
    private String warehouseId;
    private List<StockProduct> stockProducts;
}
