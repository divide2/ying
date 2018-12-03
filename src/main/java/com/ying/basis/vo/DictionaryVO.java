package com.ying.basis.vo;

import com.ying.basis.model.Dictionary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bvvy
 * @date 2018/8/10
 */
@Data
@ApiModel("字典")
public class DictionaryVO {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 分组名
     */
    @ApiModelProperty("分组名")
    private String groupName;

    /**
     * 分组code
     */
    @ApiModelProperty("分组code")
    private String groupCode;

    /**
     * 中文
     */
    @ApiModelProperty("中文")
    private String nameCN;

    /**
     * 英文
     */
    @ApiModelProperty("英文")
    private String nameEN;

    /**
     * code
     */
    @ApiModelProperty("code")
    private String code;

    /**
     * 值
     */
    @ApiModelProperty("值")
    private String value;

    /**
     * 组内排序
     */
    @ApiModelProperty("组内排序")
    private Integer orderNum;

    public static DictionaryVO of(Dictionary dictionary) {
        DictionaryVO vo = new DictionaryVO();
        vo.setCode(dictionary.getCode());
        vo.setGroupCode(dictionary.getGroupCode());
        vo.setGroupName(dictionary.getGroupName());
        vo.setId(dictionary.getId());
        vo.setNameCN(dictionary.getNameCN());
        vo.setNameEN(dictionary.getNameEN());
        vo.setOrderNum(dictionary.getOrderNum());
        vo.setValue(dictionary.getValue());
        return vo;
    }
}
