package com.mj.biz.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author bvvy
 * 有班级信息的学生信息
 */
@Data
@AllArgsConstructor
public class StudentWithClazzNameVO {
    /**
     * id
     */
    private Integer stuId;
    /**
     * 姓名
     */
    private String stuName;
    /**
     * 性别
     */
    private String gender;

    /**
     * 班级id
     */
    private Integer clazzId;

    /**
     * 名称
     */
    private String clazzName;
}
