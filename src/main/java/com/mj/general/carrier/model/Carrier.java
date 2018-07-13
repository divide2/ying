package com.mj.general.carrier.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 是否删除
     */
    @Builder.Default
    private char deleted = 'N';

    /**
     * 船公司简称
     */
    @Column(name = "carrier_code")
    private String carrierCode;

    /**
     * 船公司中文名
     */
    private String carrierCN;

    /**
     * 船公司英文名
     */
    private String carrierEN;

    /**
     * 禁用状态
     */
    @Builder.Default
    private char status = 'Y';

}
