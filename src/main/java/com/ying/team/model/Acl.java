package com.ying.team.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author bvvy
 */
@Entity
@Table(name = "t_acl")
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
