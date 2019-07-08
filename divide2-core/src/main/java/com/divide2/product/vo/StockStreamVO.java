package com.divide2.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2019/3/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockStreamVO {
    private String teamId;
    private String warehouseId;
    private String productId;
    private String productSpecId;
    private String productName;
    private String productSpecName;
    /**
     * 出入库的方式
     */
    private String type;
    /**
     * 出入库数量  入库为正 出库为负
     */
    private Integer stream;

    /**
     * 出入库后总数
     */
    private Integer amount;

    private LocalDateTime createTime;

}
