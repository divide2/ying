package com.mj.biz.stu.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bvvy
 * @date xx
 * 班级
 */
@Data
@Entity
@Table(name = "t_clazz")
public class Clazz {

    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 名称
     */
    private String name;
}
