package com.mj.model;

import lombok.*;

/**
 * Created by bvvy on 2017/12/3.
 * com.mj.model
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCountOfClz {
    private Long userNum;
    private Integer clzId;
    private String clzName;
}
