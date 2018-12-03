package com.ying.auth.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户组 具有权限相关的数据与组关联 而不是与用户关联
 * @author bvvy
 * @date 2018/8/25
 */
@Entity
@Table(name = "sys_group")
@Data
public class Group {

    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 父级id
     */
    private Integer pid;
}
