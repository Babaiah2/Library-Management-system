package com.SpringMarch.Library_Management_System.Controller;

import com.SpringMarch.Library_Management_System.DTO.StudentRequestDto;
import com.SpringMarch.Library_Management_System.DTO.StudentResponseDto;
import com.SpringMarch.Library_Management_System.DTO.StudentUpdateEmailRequestDto;
import com.SpringMarch.Library_Management_System.Entity.Student;
import com.SpringMarch.Library_Management_System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto){
        studentService.addStudent(studentRequestDto);
        return "Student has been added";
    }

    @GetMapping("/find_by_email")
    public String findStudentByEmail(@RequestParam("email") String email){
        return studentService.findByEmail(email);

    }
    @GetMapping("/find_by_age")
    public List<Student> findByAge(@RequestParam("age") int age) {
        return studentService.findByAge(age);
    }

    @PutMapping("/update_email")
    public StudentResponseDto updateStudentEmail(@RequestBody StudentUpdateEmailRequestDto studentUpdateEmailRequestDto){
        return studentService.updateStudentEmail(studentUpdateEmailRequestDto);
    }

}
