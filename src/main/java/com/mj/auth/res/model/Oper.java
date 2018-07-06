package com.mj.auth.res.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bvvy
 *
 * 系统操作
 */
@Entity
@Table(name = "sys_oper")
@Data
public class Oper {

    public static final String RES_TYPE = "OPER";

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String path;
    private String method;
}
