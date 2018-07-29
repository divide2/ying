package com.mj.ocean.surcharge.vo;

import com.mj.ocean.surcharge.keeper.SurchargeKeeper;
import com.mj.ocean.surcharge.model.Surcharge;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/7/18
 */
@ApiModel("附加费查询")
@Data
public class SurchargeGroupGetVO {
    /**
     * 船公司id
     */
    @ApiModelProperty("船公司id")
    private Integer carrierId;
    /**
     * 船公司id
     */
    @ApiModelProperty("船公司名字")
    private String carrierName;

    /**
     * 起运港口/组合 id
     */
    @ApiModelProperty("起运港口/组合 id")
    private Integer pomId;


    /**
     * 起运港口/组合 id
     */
    @ApiModelProperty("起运港口/组合名称")
    private String pomName;
    /**
     * 目的港口/组合
     */
    @ApiModelProperty(" 目的港口/组合")
    private Integer podId;

    /**
     * 目的港口/组合
     */
    @ApiModelProperty(" 目的港口/组合名称")
    private String podName;

    @ApiModelProperty("类型")
    private String costType;
    /**
     * 单个附加费信息
     */
    @ApiModelProperty("单个附加费信息")
    private List<SurchargeKeeper> surcharges;

    /**
     * 没有设置 keeper对象
     *
     * @param surcharge surcharge
     * @return vo
     */
    public static SurchargeGroupGetVO ofSurcharge(Surcharge surcharge) {

        SurchargeGroupGetVO group = new SurchargeGroupGetVO();
        group.setCarrierId(surcharge.getCarrierId());
        group.setCarrierName(surcharge.getCarrierName());
        group.setPodId(surcharge.getPodId());
        group.setPodName(surcharge.getPodName());
        group.setPomId(surcharge.getPomId());
        group.setPomName(surcharge.getPomName());
        group.setCostType(surcharge.getCostType());
        return group;
    }

}
