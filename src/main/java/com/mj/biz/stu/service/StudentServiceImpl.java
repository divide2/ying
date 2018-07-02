package com.mj.biz.stu.service;

import com.mj.biz.stu.model.Student;
import com.mj.biz.stu.repo.StudentRepository;
import com.mj.biz.stu.vo.StudentWithClazzNameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private  StudentRepository studentRepository;


    @Override
    public void add(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void update(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentWithClazzNameVO getWithClazzName(Integer id) {
        return studentRepository.getWithClazzName(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }


    @Override
    public Iterable<Student> page(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}
