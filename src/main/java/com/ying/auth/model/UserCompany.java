package com.ying.auth.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author bvvy
 * @date 2018/12/4
 */
@Entity
@Table(name="sys_user_company")
@Data
public class UserCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer companyId;
    private Integer userId;
}
