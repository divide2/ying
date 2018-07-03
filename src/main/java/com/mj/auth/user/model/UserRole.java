package com.mj.auth.user.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user_role")
@Data
public class UserRole {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer roleId;
}
