package com.mj.biz.demo.controller;

import com.mj.biz.demo.model.Student;
import com.mj.biz.demo.service.StudentService;
import com.mj.biz.demo.dto.StudentDTO;
import com.mj.biz.demo.vo.StudentWithClazzNameVO;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
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
    public ResponseEntity<Messager> add(@RequestBody StudentDTO studentDTO, BindingResult br) {
        Student student = Student.builder().name(studentDTO.getName())
                .gender(studentDTO.getGender())
                .clazzId(studentDTO.getClazzId()).build();
        studentService.add(student);
        return Responser.created();
    }

    @ApiOperation("获取有班级的学生信息")
    @GetMapping("/{stuId}/with/clz")
    public ResponseEntity<StudentWithClazzNameVO> getWithClazzName(@PathVariable Integer stuId) {
        return ResponseEntity.ok(studentService.getWithClazzName(stuId));
    }


}

