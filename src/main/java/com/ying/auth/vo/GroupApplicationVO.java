package com.ying.auth.vo;

import com.ying.auth.model.GroupApplication;
import com.ying.core.root.VO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 * @date 2019/2/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupApplicationVO implements VO<GroupApplication> {
    private String id;

    private String memoName;

    private String status;

    private UserVO user;
}
