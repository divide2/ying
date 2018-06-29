package com.mj.model;

import lombok.*;

/**
 * Created by bvvy on 2017/12/3.
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserWithoutPassword {
    private int id;
    private String username;
    private String nickname;
}
