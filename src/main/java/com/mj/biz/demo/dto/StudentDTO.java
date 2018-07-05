package com.mj.biz.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * @author bvvy
 * 学生信息
 */
@Data
@ApiModel(value = "学生", description = "学生信息")
public class StudentDTO {
    @ApiModelProperty("学生姓名")
    @Length(min = 2, max = 4)
    private String name;

    @ApiModelProperty("学生性别")
    private String gender;

    @NotEmpty
    @ApiModelProperty("学生班级")
    @Min(0)
    private Integer clazzId;

}
