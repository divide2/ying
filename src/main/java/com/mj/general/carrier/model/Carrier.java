package com.mj.general.carrier.model;

import lombok.*;
import javax.persistence.*;

/**
 * @author zejun
 * @date
 * 船公司管理表
 */
@Data
@Entity
@Table(name = "general_carrier")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carrier {
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
     * 船公司中文名
     */
    @Column(name = "carrier_cn")
    private String carrierCN;

    /**
     * 船公司英文名
     */
    @Column(name = "carrier_en")
    private String carrierEN;

    /**
     * 客户公司id
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 禁用状态
     */
    @Builder.Default
    private Character enabled = 'Y';

}
