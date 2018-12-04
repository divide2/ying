package com.ying.auth.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 存储所有的api信息
 *
 * @author bvvy
 */
@Entity
@Table(name = "sys_api")
@Data
public class Api {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String path;
    private String method;
}
