package com.mj.general.dictionary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author zejun
 * @date 2018/7/12 14:41
 */
@Data
@Entity
@Table(name = "general_country")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralCountry {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 国家（地区）代码
     */
    @Column(name = "country_code")
    private String countryCode;

    /**
     * 国家（地区）中文名
     */
    private String countryCN;

    /**
     * 国家（地区）英文名
     */
    private String countryEN;
}
