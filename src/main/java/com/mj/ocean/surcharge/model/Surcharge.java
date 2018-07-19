package com.mj.ocean.surcharge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 附加费
 *
 * @author bvvy
 * @date 2018/7/18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ocean_fc_surcharge")
public class Surcharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 船公司id
     */
    private Integer carrierId;
    /**
     * 船公司名称
     */
    private String carrierName;
    /**
     * 起运港口/组合 id
     */
    private Integer pomId;
    /**
     * 启运港/组合名称
     */
    private String pomName;


    /**
     * 目的港口/组合
     */
    private Integer podId;

    /**
     * 目的港/组合名称
     */
    private String podName;

    /**
     * 简称/编码
     */
    private String code;
    /**
     * 中文
     */
    private String nameCn;
    /**
     * 英文
     */
    private String nameEn;
    /**
     * 计费单位
     */
    private String billingUnit;
    /**
     * 币种
     */
    private String currency;
    /**
     * 金额
     */
    private BigDecimal amt;
    /**
     * 支付方式
     */
    private String payWay;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 启用
     */
    private String enabled;

}
