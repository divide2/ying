package com.divide2.friend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author bvvy
 * @date 2018/12/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class MessageDTO {

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Integer fromId;

    @NotNull
    private Integer toId;

    @NotBlank
    private String content;
}
