package com.mj.model;

import lombok.*;

/**
 *
 * @author bvvy
 * @date 2017/12/3
 * com.mj.model
 */
@Data
@Builder
@NoArgsConstructor
public class UserCountOfClz {
    private Long userNum;
    private Integer clzId;
    private String clzName;
}
