package com.hlsb.model;

import lombok.*;

/**
 * Created by bvvy on 2017/12/3.
 * com.hlsb.model
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserCountOfClz {
    private Long userNum;
    private Integer clzId;
    private String clzName;
}
