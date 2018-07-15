package com.mj.biz.demo.repo.custom;

import com.mj.biz.demo.dto.StudentQueryDTO;
import com.mj.biz.demo.vo.StudentWithClazzNameVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2017/12/4
 * com.mj.demo.repository
 */
public interface StudentRepositoryCustom {


    /**
     * 获取有班级名称的学生信息
     *
     * @param id 学生id
     * @return studentClassVO
     */
    StudentWithClazzNameVO getWithClazzName(Integer id);

    /**
     * 获取有班级的学生
     * @param queryDTO 搜索条件
     * @param pageable 分页
     * @return student with class name
     */
    Page<StudentWithClazzNameVO> findWithClassName(StudentQueryDTO queryDTO, Pageable pageable);
}
