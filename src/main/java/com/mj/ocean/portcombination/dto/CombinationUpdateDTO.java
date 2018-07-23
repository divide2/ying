package com.mj.ocean.portcombination.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zejun
 * @date 2018/7/13 17:40
 */
@Data
@ApiModel("修改港口组")
public class CombinationUpdateDTO {

    @ApiModelProperty("港口组id")
    @NotNull
    private Integer id;

    @ApiModelProperty("组合名称")
    @NotEmpty
    private String combinationName;

    @ApiModelProperty("船公司港口")
    @NotEmpty
    private List<CarrierPort> carrierPorts;

    @Data
    public static class CarrierPort {
        private Integer carrierId;
        private List<Integer> portIds;
    }
}
