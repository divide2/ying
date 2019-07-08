package com.divide2.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 * @date 2019/2/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleMenuChatDTO {

    private String menuCode;
    private Integer userId;
}
