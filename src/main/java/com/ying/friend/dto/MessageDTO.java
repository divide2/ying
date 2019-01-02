package com.ying.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 * @date 2018/12/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private  String fromName;
    private  Integer toId;
    private  String content;
}
