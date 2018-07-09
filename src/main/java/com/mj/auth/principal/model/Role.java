package com.mj.auth.principal.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author bvvy
 * <p>
 * 角色
 */
@Data
@Entity
@Table(name = "sys_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    public static final String PRINCIPAL = "ROLE";

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
