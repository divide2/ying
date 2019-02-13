package com.ying.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * @author bvvy
 */
@Entity
@Table(name = "sys_acl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Acl {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer roleId;

    private String resCode;

}
