package com.ying.auth.acl.vo;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

/**
 * @author bvvy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AclBO {

    private Integer aclState;
    private Integer resId;
    private String resLabel;
    private Integer resPid;
    private String path;
    private Integer orderNum;
}
