package com.mj.auth.user.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "sys_user")
public class User {
        @Id
        @GeneratedValue
        private Integer id;

        private String username;

        private String password;

        private String nickname;

        private String phone;

        private String email;

        private String gender;

        private String avatar;

        private boolean enabled;
}
