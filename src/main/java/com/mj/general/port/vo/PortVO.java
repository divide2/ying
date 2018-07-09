package com.mj.general.port.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @auther: zejun
 * @date: 2018/7/9 18:03
 */
@Data
@Builder
public class PortVO {

    @A
    private Integer id;

    private String portCode;

    private String portCN;

    private String portEN;

    private String countryCN;

    private String countryEN;

    private String countryCode;

    private String serviceName;

    private String status;
}
