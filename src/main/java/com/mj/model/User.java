package com.mj.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by bvvy on 2017/12/3.
 */
@Entity
@Table(name = "t_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    @Column(name = "clz_id")
    private Integer clzId;
    @Column(name = "clz_name")
    private String clzName;


}
