package com.mj.biz.demo.service;

import com.mj.biz.demo.model.Student;
import com.mj.biz.demo.vo.StudentWithClazzNameVO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author bvvy
 * 学生service
 */
public interface StudentService {
    /**
     * 添加
     * @param student demo
     */
    void add(Student student);

    /**
     * 修改
     * @param student demo
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
     * @return demo
     */
    StudentWithClazzNameVO getWithClazzName(Integer id);

    /**
     * 获取全部
     * @return list demo
     */
    List<Student> findAll();

    /**
     * 分页
     * @param pageable 页码
     * @return page
     */
    Iterable<Student> page(Pageable pageable);

}
