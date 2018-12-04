package com.ying.auth.model;

import lombok.Data;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
