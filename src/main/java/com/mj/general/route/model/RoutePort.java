package com.mj.general.route.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zejun
 * @date 2018/7/10 11:15
 */
@Data
@Entity
@Table(name = "general_route_port")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoutePort {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 港口id
     */
    @Column(name = "port_id")
    private Integer portId;

    /**
     * 港口英文名
     */
    @Column(name = "port_en")
    private String portEN;

    /**
     * 国家（地区）英文名
     */
    @Column(name = "country_en")
    private String countryEN;

    /**
     * 截关时间
     */
    private LocalDateTime etc;

    /**
     * 开船时间
     */
    private LocalDateTime etd;

    /**
     * 航程
     */
    private BigDecimal tt;

    /**
     * 码头
     */
    private String pier;

    /**
     * 排序
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 航线id
     */
    @Column(name = "route_id")
    private Integer routeId;
}
