package com.ying.core.root.example;

import com.ying.core.root.dto.DTO;
import com.ying.core.root.dto.anno.RelateColumn;
import com.ying.core.root.dto.anno.RelateTable;
import com.ying.core.root.dto.anno.Tables;
import lombok.Data;

/**
 *   多表和单表问题 todo 先解决单表
 * @author bvvy
 * @date 2018/12/20
 */
@Tables({
        @RelateTable(entity = Student.class,alias = "s"),
        @RelateTable(entity = Clazz.class, alias = "c", joinType = "left join")
})
@Data
public class ExampleDTO implements DTO {

    @RelateColumn(value = "id",tableAlias = "s")
    private Integer stuId;
    private String stuName;

    @RelateColumn(value = "id",tableAlias = "c")
    private Integer classId;
    private String className;
}
