package com.ying.auth.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户的组
 *
 * @author bvvy
 * @date 2018/8/25
 */

@Entity
@Table(name = "sys_user_group")
@Data
public class UserGroup {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer groupId;
}
