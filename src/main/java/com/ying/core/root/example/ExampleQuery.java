package com.ying.core.root.example;

import com.ying.core.root.query.QueryField;
import com.ying.core.root.query.QueryParam;
import lombok.Data;

/**
 * select * from stu s left join clazz c
 * where s.id = 1 and  s.name like "xx"
 * and c.name like 'xx'
 * 多表和单表问题 todo 先解决单表
 * @author bvvy
 * @date 2018/12/20
 */
@Data
public class ExampleQuery implements QueryParam {

    @QueryField(value = {"stuName","clazzName"},op = QueryField.OP.LIKE )
    private String stuOrClazzName;

    @QueryField(value = "stuId", op = QueryField.OP.EQ)
    private String stuId;
}
