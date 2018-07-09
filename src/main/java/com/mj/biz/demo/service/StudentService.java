package com.mj.biz.demo.service;

import com.mj.biz.demo.model.Student;
import com.mj.biz.demo.vo.StudentWithClazzNameVO;
import com.mj.core.service.BasicService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * 学生service
 */
public interface StudentService extends BasicService<Student,Integer> {



    /**
     * 获取有班级的学生信息
     * @param id id
     * @return demo
     */
    StudentWithClazzNameVO getWithClazzName(Integer id);

    /**
     * 单表查询获取数据
     * @param predicate 查询条件
     * @param pageable 分页排序信息
     * @return 结果
     */
     Page<Student> find(Predicate predicate, Pageable pageable);

}
