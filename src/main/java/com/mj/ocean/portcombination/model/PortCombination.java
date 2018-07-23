package com.mj.ocean.portcombination.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author zejun
 * @date 2018/7/13 15:08
 */
@Data
@Entity
@Table(name = "ocean_fc_port_combination")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortCombination {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 组合名称
     */
    @Column(name = "combination_name")
    private String combinationName;

    /**
     * 是否启用。 默认Y；Y：启用；N：不可用
     */
    @Builder.Default
    private Character enabled = 'Y';

    /**
     * 客户公司id
     */
    @Column(name = "company_id")
    private Integer companyId;
}
