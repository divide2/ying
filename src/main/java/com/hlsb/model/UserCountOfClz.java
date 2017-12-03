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
    private long userNum;
    private int clzId;
    private String clzName;
}
