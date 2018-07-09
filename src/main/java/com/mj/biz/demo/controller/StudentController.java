package com.mj.biz.demo.controller;

import com.mj.biz.demo.dto.StudentAddDTO;
import com.mj.biz.demo.model.Student;
import com.mj.biz.demo.service.StudentService;
import com.mj.biz.demo.vo.StudentWithClazzNameVO;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author bvvy
 * 学生controller
 */
@RestController
@RequestMapping("/v1/student")
@Api(value = "Student", tags = "学生操作")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/")
    @ApiOperation("添加学生")
    public ResponseEntity<Messager> add(@Valid @RequestBody StudentAddDTO studentAddDTO, BindingResult br) {
        Student student = Student.builder().name(studentAddDTO.getName())
                .gender(studentAddDTO.getGender())
                .clazzId(studentAddDTO.getClazzId()).build();
        studentService.add(student);
        return Responser.created();
    }

    @ApiOperation("获取有班级的学生信息")
    @GetMapping("/{stuId}/with/clz")
    public ResponseEntity<StudentWithClazzNameVO> getWithClazzName(@PathVariable Integer stuId) {
        return ResponseEntity.ok(studentService.getWithClazzName(stuId));
    }

    @GetMapping("/list")
    /**
     * 单表模糊查询 并返回数据
     */
    public ResponseEntity<Page<Student>> findStudent(@QuerydslPredicate(root = Student.class) Predicate predicate, Pageable pageable) {
        Page<Student> students = studentService.find(predicate, pageable);
        return ResponseEntity.ok(students);
    }

    @PatchMapping
    public void update() {

    }


    @DeleteMapping
    public void delete() {

    }
}

