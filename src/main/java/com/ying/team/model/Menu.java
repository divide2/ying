package com.ying.team.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author bvvy
 * <p>
 * 菜单
 */
@Entity
@Table(name = "t_menu")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    /**
     * 名称
     */
    private String name;

    /**
     * 父级菜单
     */
    private Integer pid;
    /**
     * 地址
     */
    private String path;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 排序号
     */
    @Column(name = "order_num")
    private Integer orderNum;


    private String menuCode;

    /**
     * tubiao
     */
    private String icon;

}
