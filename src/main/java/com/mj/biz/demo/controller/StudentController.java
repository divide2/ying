package com.mj.biz.demo.controller;

import com.mj.biz.demo.model.Student;
import com.mj.biz.demo.service.StudentService;
import com.mj.biz.demo.to.StudentTO;
import com.mj.biz.demo.vo.StudentWithClazzNameVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author bvvy
 * 学生controller
 */
@RestController
@RequestMapping("/v1/student")
@Api(value = "Student",tags = "学生操作")
public class StudentController {

    @Autowired
    private  StudentService studentService;


    @PostMapping("/")
    @ApiOperation("添加学生")
    public ResponseEntity<Conflict> add(@RequestBody StudentTO studentTO, BindingResult br) {
        if (br.hasErrors()) {
            return ResponseEntity.status(417).body(new Conflict(br.getAllErrors().toString()));
        }
        Student student = Student.builder().name(studentTO.getName())
                .gender(studentTO.getGender())
                .clazzId(studentTO.getClazzId()).build();
        studentService.add(student);
        return ResponseEntity.status(201).body(new Conflict("成功了"));
    }

    @ApiOperation("获取有班级的学生信息")
    @GetMapping("/{stuId}/with/clz")
    public ResponseEntity<StudentWithClazzNameVO> getWithClazzName(@PathVariable Integer stuId) {
        return ResponseEntity.ok(studentService.getWithClazzName(stuId));
    }

    @PatchMapping
    public void update() {

    }


    @DeleteMapping
    public void delete() {

    }
}

