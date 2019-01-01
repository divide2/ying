package com.ying.friend.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bvvy
 * @date 2018/12/11
 */
@Data
public class FriendVO {


    private String id;

    @ApiModelProperty("朋友的id")
    private Integer toId;

    @ApiModelProperty("备注名")
    private String memoName;

    @ApiModelProperty("朋友公司")
    private Integer companyId;

    @ApiModelProperty("朋友名称")
    private String companyName;

    private String avatar;


}
