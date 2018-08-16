package com.ying.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/8/16
 */
@Data
@Entity
@Table(name = "p_product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 主图
     */
    private String mainImg;
    /**
     * 标签
     */
    private String tags;
    /**
     * price
     */
    private String price;
    /**
     * 创建人
     */
    private String fromName;
    /**
     * 创建人
     */
    private Integer fromId;
    /**
     * 创建日期
     */
    private LocalDateTime cdt;
    /**
     * 创建地点
     */
    private String cdp;
    /**
     * 经纬度
     */
    private BigDecimal longitude;
    /**
     * 经纬度
     */
    private BigDecimal latitude;
    /**
     * 启用
     */
    private String enabled;
    /**
     * 备注 描述
     */
    private String remarks;

}
