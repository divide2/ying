package com.mj.biz.stu.service;

import com.mj.biz.stu.model.Student;
import com.mj.biz.stu.vo.StudentWithClazzNameVO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author bvvy
 */
public interface StudentService {
    /**
     * 添加
     * @param student stu
     */
    void add(Student student);

    /**
     * 修改
     * @param student stu
     */
    void update(Student student);

    /**
     * 删除
     * @param id id
     */
    void delete(Integer id);

    /**
     * 获取有班级的学生信息
     * @param id id
     * @return stu
     */
    StudentWithClazzNameVO getWithClazzName(Integer id);

    /**
     * 获取全部
     * @return list stu
     */
    List<Student> findAll();

    /**
     * 分页
     * @param pageable 页码
     * @return page
     */
    Iterable<Student> page(Pageable pageable);

}
