package com.mj.ocean.portcombination.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zejun
 * @date 2018/7/13 17:06
 */
@Data
@ApiModel("新增港口组")
public class CombinationAddDTO {

    @ApiModelProperty("船公司id")
    @NotNull
    private Integer carrierId;

    @ApiModelProperty("组合港口")
    @NotEmpty
    private List<CombinationrPort> combinationrPorts;

    @Data
    public static class CombinationrPort {
        @ApiModelProperty("组合名字/多个")
        @NotEmpty
        private List<String> combinationNames;

        @ApiModelProperty("港口ids")
        @NotEmpty
        private List<Integer> portIds;
    }
}
