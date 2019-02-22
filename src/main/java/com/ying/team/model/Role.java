package com.ying.team.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bvvy
 * <p>
 * 角色
 */
@Data
@Entity
@Table(name = "t_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    public static final String PRINCIPAL = "ROLE";

    /**
     * id
     */
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;
}
