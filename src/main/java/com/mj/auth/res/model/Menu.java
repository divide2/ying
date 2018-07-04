package com.mj.auth.res.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_menu")
@Data
public class Menu {

    public static final String RES_TYPE = "MENU";
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 名称
     */
    private String name;

    private Integer pid;

    private String path;

    private Boolean enabled;

    private Integer orderNum;


}
