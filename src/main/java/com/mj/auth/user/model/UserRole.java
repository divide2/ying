package com.mj.auth.user.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bvvy
 * <p>
 * 用户角色关联
 */
@Entity
@Table(name = "sys_user_role")
@Data
public class UserRole {
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 角色id
     */
    private Integer roleId;
}
