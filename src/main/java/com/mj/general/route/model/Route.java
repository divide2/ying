package com.mj.general.route.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author zejun
 * @date 2018/7/10 10:37
 */
@Data
@Entity
@Table(name = "general_route")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 船公司简称
     */
    @Column(name = "carrier_code")
    private String carrierCode;

    /**
     * 船公司英文名
     */
    @Column(name = "carrier_en")
    private String carrierEN;

    /**
     * 船公司id
     */
    @Column(name = "carrier_id")
    private Integer carrierId;

    /**
     * 航线代码
     */
    @Column(name = "route_code")
    private String routeCode;

    /**
     *航线全称
     */
    @Column(name = "route_full_name")
    private String routeFullName;

    /**
     * 航线描述
     */
    @Column(name = "route_desc")
    private String routeDesc;

    /**
     * 航线图
     */
    @Column(name = "route_map_url")
    private String routeMapUrl;

    /**
     * 全程时间
     */
    @Column(name = "all_time")
    private BigDecimal allTime;

    /**
     * 首靠港
     */
    @Column(name = "first_port")
    private String firstPort;

    /**
     * 最后靠港
     */
    @Column(name = "last_port")
    private String lastPort;

    /**
     * 总的港口数
     */
    private Integer num;

    /**
     * 全部港口
     */
    @Column(name = "all_port")
    private String allPort;

    /**
     * 禁用状态
     */
    @Builder.Default
    private Character enabled = 'Y';

    /**
     * 客户公司id
     */
    @Column(name = "company_id")
    private Integer companyId;
}
