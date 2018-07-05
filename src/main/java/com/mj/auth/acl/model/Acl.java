package com.mj.auth.acl.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author bvvy
 *
 *
 */
@Entity
@Table(name = "sys_acl")
@Data
public class Acl {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "principal_id")
    private Integer principalId;


    @Column(name = "res_id")
    private Integer resId;

    @Column(name = "principal_type")
    private String principalType;

    @Column(name = "res_type")
    private String resType;

    @Column(name = "acl_status")
    private Integer aclStatus;

}
