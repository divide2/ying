package com.mj.ocean.surcharge.dto;

import com.mj.ocean.surcharge.keeper.SurchargeKeeper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/7/18
 */
@ApiModel("附加费更新")
@Data
public class SurchargeUpdateDTO {

    /**
     * 船公司id
     */
    @ApiModelProperty("船公司id")
    @NotNull
    private Integer carrierId;
    /**
     * 起运港口/组合 id
     */
    @ApiModelProperty("起运港口/组合 id")
    @NotNull
    private Integer pomId;
    /**
     * 目的港口/组合
     */
    @ApiModelProperty(" 目的港口/组合")
    @NotNull
    private Integer podId;

    @ApiModelProperty("费用类型")
    @NotEmpty
    private String costType;

    /**
     * 费用信息
     */
    @NotEmpty
    @ApiModelProperty("费用信息")
    private List<SurchargeKeeper> surcharges;

}
