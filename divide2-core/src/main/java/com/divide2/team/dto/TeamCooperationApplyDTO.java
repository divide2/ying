package com.divide2.team.dto;

import com.divide2.core.root.dto.DTO;
import com.divide2.team.model.TeamCooperationApplication;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 * @date 2019/2/19
 */
@Data
public class TeamCooperationApplyDTO implements DTO<TeamCooperationApplication> {
    @NotEmpty
    private String fromTeamId;

    @NotEmpty
    private String toTeamId;
    private String remarks;
}
