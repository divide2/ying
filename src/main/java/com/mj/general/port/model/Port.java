package com.mj.general.port.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @auther: zejun
 * @date: 2018/7/9 17:36
 * 世界港口表
 */
@Data
@Entity
@Table(name = "general_port")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Port {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 是否删除
     */
    @Builder.Default
    private Integer deleted = 0;

    /**
     * 港口代码
     */
    @Column(name = "port_code")
    private String portCode;

    /**
     * 港口中文名
     */
    private String portCN;

    /**
     * 港口英文名
     */
    private String portEN;

    /**
     * 国家（地区）中文名
     */
    private String countryCN;

    /**
     * 国家（地区）英文名
     */
    private String countryEN;

    /**
     * 国家（地区）代码
     */
    @Column(name = "country_code")
    private String countryCode;

    /**
     * 所属航线
     */
    @Column(name = "service_name")
    private String serviceName;

    /**
     * 禁用状态
     */
    @Builder.Default
    private String status = "0";
}
