package com.mj.general.dictionary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author zejun
 * @date 2018/7/12 14:32
 */
@Data
@Entity
@Table(name = "general_info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralInfo {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分组名
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 分组code
     */
    @Column(name = "group_code")
    private String groupCode;

    /**
     * 名称
     */
    private String name;

    /**
     * code
     */
    private String code;

    /**
     * 组内排序
     */
    @Column(name = "order_num")
    private Integer orderNum;
}
