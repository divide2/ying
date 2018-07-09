package com.mj.biz.demo.service.impl;

import com.mj.biz.demo.model.Student;
import com.mj.biz.demo.repo.StudentRepository;
import com.mj.biz.demo.service.StudentService;
import com.mj.biz.demo.vo.StudentWithClazzNameVO;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 */
@Service
public class StudentServiceImpl extends SimpleBasicServiceImpl<Student, Integer, StudentRepository> implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public StudentWithClazzNameVO getWithClazzName(Integer id) {
        return studentRepository.getWithClazzName(id);
    }

    @Override
    public Page<Student> find(Predicate predicate, Pageable pageable) {
        return studentRepository.findAll(predicate, pageable);
    }
}
