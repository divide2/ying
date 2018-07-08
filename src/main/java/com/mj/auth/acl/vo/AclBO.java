package com.mj.auth.acl.vo;

        import lombok.AllArgsConstructor;
        import lombok.Data;

@Data
@AllArgsConstructor
public class AclBO {

    private Integer aclState;
    private Integer resId;
    private String resLabel;
    private Integer resPid;
}
