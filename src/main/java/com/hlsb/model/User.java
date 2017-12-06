package com.hlsb.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Created by bvvy on 2017/12/3.
 */
@Entity
@Table(name = "t_user")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getClzId() {
        return clzId;
    }

    public void setClzId(Integer clzId) {
        this.clzId = clzId;
    }

    public String getClzName() {
        return clzName;
    }

    public void setClzName(String clzName) {
        this.clzName = clzName;
    }
}
