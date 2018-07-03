package com.mj.biz.demo.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;


/**
 * @author bvvy
 * 学生信息
 */
@Data
@ApiModel(value = "学生", description = "学生信息")
public class StudentTO {
    @ApiModelProperty("学生姓名")
    @Length(min = 2, max = 4)
    private String name;

    @ApiModelProperty("学生性别")
    private String gender;

    @ApiModelProperty("学生班级")
    @Min(0)
    private Integer clazzId;

}
