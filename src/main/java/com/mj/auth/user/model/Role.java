package com.mj.auth.user.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sys_role")
public class Role {
    @Id
    @GeneratedValue
    private Integer id;
    private String code;
    private String name;
}
