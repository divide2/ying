package com.mj.model;

import lombok.*;

/**
 *
 * @author bvvy
 * @date 2017/12/3
 */
@Data
@Builder
@NoArgsConstructor
public class UserWithoutPassword {
    private int id;
    private String username;
    private String nickname;
}
