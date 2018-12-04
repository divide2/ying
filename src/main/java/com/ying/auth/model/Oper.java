package com.ying.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author bvvy
 *
 * 系统操作
 */
@Entity
@Table(name = "sys_oper")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Oper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer resId;
    @Column(name = "index_pos")
    private Integer indexPos;
    private String code;
    private String apis;
}
