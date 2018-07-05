package com.mj.auth.res.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * @author bvvy
 * <p>
 * 菜单
 */
@Entity
@Table(name = "sys_menu")
@Data
@Builder
public class Menu {

    public static final String RES_TYPE = "MENU";
    @Id
    @GeneratedValue
    private Integer id;
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


}
