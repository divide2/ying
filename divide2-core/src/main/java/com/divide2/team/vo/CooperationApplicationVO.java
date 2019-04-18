package com.divide2.team.vo;

import lombok.Data;

/**
 * @author bvvy
 * @date 2019/2/20
 */
@Data
public class CooperationApplicationVO {

    private String id;
    private String remarks;
    private String status;
    private boolean selfApply;
    private TeamVO team;

}
