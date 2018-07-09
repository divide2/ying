package com.mj.auth.res.model;

import lombok.Data;

import javax.persistence.*;

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
