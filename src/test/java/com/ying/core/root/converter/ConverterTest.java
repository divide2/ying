package com.ying.core.root.converter;

import com.ying.core.root.example.Student;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/28
 */
public class ConverterTest {

    @Test
    public void test() {
        List<Student> students = new ArrayList<Student>() {{
            add(new Student() {{
                setId(1);
                setName("xiaoming");
            }});
        }};
        Converter<Student> converter = Converter.of(students);
        List<StudentVO> vos = converter.convert(student -> new StudentVO(student.getName()));
        Assert.assertEquals(vos.size(), students.size());
    }

    @Data
    class StudentVO {
        public StudentVO(String name) {
            this.name = name;
        }

        private String name;
    }
}
