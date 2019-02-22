package com.ying.team.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author bvvy
 * @date 2019/2/13
 */
@Data
@Entity
@Table(name = "t_res")
public class Res {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

}
