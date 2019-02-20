package com.ying.auth.dto;

import com.ying.auth.model.GroupCooperationApplication;
import com.ying.core.root.dto.DTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 * @date 2019/2/19
 */
@Data
public class GroupCooperationApplyDTO implements DTO<GroupCooperationApplication> {
    @NotEmpty
    private String fromGroupId;

    @NotEmpty
    private String toGroupId;
    private String remarks;
}
