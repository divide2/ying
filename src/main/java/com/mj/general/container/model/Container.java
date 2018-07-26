package com.mj.general.container.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author zejun
 * @date 2018/7/26 15:39
 */
@Data
@Entity
@Table(name = "general_container")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Container {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 柜型简称
     */
    @Column(name = "container_code")
    private String containerCode;

    /**
     *柜型中文名
     */
    @Column(name = "container_cn")
    private String containerCN;

    /**
     *柜型英文名
     */
    @Column(name = "container_en")
    private String containerEN;

    /**
     * 禁用状态
     */
    @Builder.Default
    private Character enabled = 'Y';
}
