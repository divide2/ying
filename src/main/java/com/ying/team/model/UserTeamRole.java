package com.ying.team.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author bvvy
 * <p>
 * 用户在组里面的角色
 */
@Entity
@Table(name = "t_team_user_role")
@Data
public class UserTeamRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 团队的id
     */
    private String teamId;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 角色id
     */
    private Integer roleId;


    /**
     * 团队里备注
     */
    private String memoName;
}
