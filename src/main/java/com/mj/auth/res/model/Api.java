package com.mj.auth.res.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_api")
@Data
public class Api {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String path;
    private String method;
}
