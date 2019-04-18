package com.divide2.product.service;

import com.divide2.product.bo.StockBO;
import com.divide2.product.dto.InStockDTO;
import com.divide2.product.dto.OutStockDTO;
import com.divide2.product.model.SpecStock;
import com.divide2.product.query.StockQuery;
import com.divide2.product.vo.StockStreamVO;
import com.divide2.product.vo.StockVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 所有的对库存的操作都是两个动作 入库(in) 和 出库(out)
 * 例如确认发货 调用 out
 * 例如确认收货  调用 in
 *
 * @author bvvy
 * @date 2018/12/9
 */
public interface StockService {
    /**
     * 获取库存流水
     *
     * @param teamId
     * @param stockQuery
     * @param pageable pageable
     * @return x
     */
    Page<StockStreamVO> findStockStream(String teamId, StockQuery stockQuery, Pageable pageable);

    /**
     * 入库
     *
     * @param dto dto
     */
    void in(InStockDTO dto);

    /**
     * 出库
     *
     * @param dto dto
     */
    void out(OutStockDTO dto);


    Page<StockBO> find(StockQuery stockQuery);

    List<SpecStock> getByWarehouseId(String warehouseId);

    Page<StockVO> findByTeam(String teamId, StockQuery stockQuery, Pageable pageable);

}
