package com.ying.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author bvvy
 * @date 2018-07-25
 */
@Data
@AllArgsConstructor
public class AclOperBO {
    private Boolean aclStatus;
    private Integer operId;
    private Integer operIndexPos;
}
