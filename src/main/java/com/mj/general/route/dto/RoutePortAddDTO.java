package com.mj.general.route.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zejun
 * @date 2018/7/10 11:39
 */
@Data
@ApiModel("新增航线管理中的港口")
public class RoutePortAddDTO {

    @ApiModelProperty("港口id")
    @NotNull
    private Integer portId;

    @ApiModelProperty("截关时间")
    @NotEmpty
    private LocalDateTime etc;

    @ApiModelProperty("开船时间")
    @NotEmpty
    private LocalDateTime etd;

    @ApiModelProperty("航程")
    @NotNull
    private BigDecimal tt;

    @ApiModelProperty("码头")
    @NotEmpty
    private String pier;

    @ApiModelProperty("排序")
    @NotNull
    private Integer orderNum;

}
