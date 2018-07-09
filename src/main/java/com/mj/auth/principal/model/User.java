package com.mj.auth.principal.model;


import lombok.Data;

import javax.persistence.*;

/**
 * @author bvvy
 * 用户
 */
@Entity
@Data
@Table(name = "sys_user")
public class User {
        /**
         * id
         */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        /**
         * 用户名
         */
        private String username;

        /**
         * 密码
         */
        private String password;

        /**
         * 昵称
         */
        private String nickname;

        /**
         * 电话
         */
        private String phone;
        /**
         * 邮箱
         */
        private String email;
        /**
         * 性别
         */
        private String gender;

        /**
         * 头像
         */
        private String avatar;

        /**
         * 启用
         */
        private boolean enabled;
}
