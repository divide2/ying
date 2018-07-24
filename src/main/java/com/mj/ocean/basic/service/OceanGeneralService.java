package com.mj.ocean.basic.service;

import com.mj.general.carrier.model.Carrier;
import com.mj.general.port.model.Port;
import com.mj.general.route.model.Route;

/**
 * @author bvvy
 * @date 2018/7/19
 */
public interface OceanGeneralService {
    /**
     * 获取港口
     * @param portId portId
     * @return 港口
     */
    Port getPort(Integer portId);

    /**
     * 获取单个公司
     * @param carrierId carrierId
     * @return 公司
     */
    Carrier getCarrier(Integer carrierId);

    /**
     * 获取航线数据
     * @param routeId 航线id
     * @return Route
     */
    Route getRoute(Integer routeId);
}
