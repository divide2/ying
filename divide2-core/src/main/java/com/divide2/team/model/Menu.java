package com.divide2.team.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    public static final String DEFAULT_PID = "0";

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
    private String pid;
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

    /**
     * 图标
     */
    private String icon;

    /**
     * 授权码
     */
    private String authority;


    @Type(type = "yes_no")
    private Boolean shortcut;

    private String color;

}
