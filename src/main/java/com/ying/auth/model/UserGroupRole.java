package com.ying.auth.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author bvvy
 * <p>
 * 用户在组里面的角色
 */
@Entity
@Table(name = "sys_user_group_role")
@Data
public class UserGroupRole {
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

    /**
     * 团队的id
     */
    private String groupId;

    /**
     * 团队里备注
     */
    private String memoName;
}
