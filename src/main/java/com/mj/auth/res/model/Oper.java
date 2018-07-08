package com.mj.auth.res.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author bvvy
 *
 * 系统操作
 */
@Entity
@Table(name = "sys_oper")
@Data
public class Oper {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer resId;
    @Column(name = "index_pos")
    private Integer indexPos;
    private String code;
}
