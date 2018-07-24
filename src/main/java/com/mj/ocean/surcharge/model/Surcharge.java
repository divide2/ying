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
    @Column(name = "carrier_id")
    private Integer carrierId;
    /**
     * 船公司名称
     */
    @Column(name = "carrier_code")
    private String carrierName;
    /**
     * 起运港口/组合 id
     */
    @Column(name = "pom_id")
    private Integer pomId;
    /**
     * 启运港/组合名称
     */
    @Column(name = "pom_name")
    private String pomName;


    /**
     * 目的港口/组合
     */
    @Column(name = "pod_id")
    private Integer podId;

    /**
     * 目的港/组合名称
     */
    @Column(name = "pod_name")
    private String podName;

    /**
     * 简称/编码
     */
    private String code;
    /**
     * 中文
     */
    @Column(name = "name_cn")
    private String nameCn;
    /**
     * 英文
     */
    @Column(name = "name_en")
    private String nameEn;
    /**
     * 计费单位
     */
    @Column(name = "billing_unit")
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
    @Column(name = "pay_way")
    private String payWay;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 启用
     */
    private Character enabled;

}
