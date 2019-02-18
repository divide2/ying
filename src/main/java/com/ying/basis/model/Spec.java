package com.ying.basis.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 基础规格 规格 跟企业和人绑定 输入过的规格 用or 输入时匹配出来
 *
 * //todo not hear
 * @author bvvy
 * @date 2018/12/3
 */
@Data
@Entity
@Table(name = "basis_spec")
public class Spec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer userId;
    private Integer groupId;
}
