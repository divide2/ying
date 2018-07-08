package com.mj.auth.acl.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AclOperBO {
    private Integer aclStatus;
    private Integer operId;
    private Integer operIndexPos;
}
