package com.ying.mine.service;

import com.ying.mine.vo.WarehouseVO;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/10
 */
public interface MineService {
    /**
     * 获取用户的仓库
     * @return vo
     */
    List<WarehouseVO> listWarehouse();
}
