package com.hlsb.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Created by bvvy on 2017/12/3.
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String nickname;
    private String password;
    @Column(name = "clz_id")
    private int clzId;
    @Column(name = "clz_name")
    private String clzName;

}
