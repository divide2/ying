package com.mj.biz.demo.service;

import com.mj.biz.demo.model.Student;
import com.mj.biz.demo.vo.StudentWithClazzNameVO;
import com.mj.core.service.BasicService;

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
}
