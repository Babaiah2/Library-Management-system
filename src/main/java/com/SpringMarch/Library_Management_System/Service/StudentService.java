package com.SpringMarch.Library_Management_System.Service;

import com.SpringMarch.Library_Management_System.DTO.StudentRequestDto;
import com.SpringMarch.Library_Management_System.DTO.StudentResponseDto;
import com.SpringMarch.Library_Management_System.DTO.StudentUpdateEmailRequestDto;
import com.SpringMarch.Library_Management_System.Entity.LibraryCard;
import com.SpringMarch.Library_Management_System.Entity.Student;
import com.SpringMarch.Library_Management_System.Enum.CardStatus;
import com.SpringMarch.Library_Management_System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void addStudent(StudentRequestDto studentRequestDto){

        //create a student object
        Student student = new Student();

        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());

        //Create a card Object
         LibraryCard card = new LibraryCard();
         card.setStatus(CardStatus.ACTIVATED);
         card.setStudent(student);// if not set this student to card fk student id is null

         //set card to student
         student.setCard(card);

         studentRepository.save(student);
    }
    public String findByEmail(String email){
        Student student = studentRepository.findByEmail(email);
        return student.getName();
    }

    public List<Student> findByAge(int age){
        return studentRepository.findByAge(age);
    }

    public StudentResponseDto updateStudentEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto){

        Student student = studentRepository.findById(studentUpdateEmailRequestDto.getId()).get();
        student.setEmail(studentUpdateEmailRequestDto.getEmail());

        //update step
        Student updatedStudent = studentRepository.save(student);

        //convert updated student into studentResponseDto

        StudentResponseDto studentResponseDto = new StudentResponseDto();

        studentResponseDto.setId(updatedStudent.getId());
        studentResponseDto.setName(updatedStudent.getName());
        studentResponseDto.setEmail(updatedStudent.getEmail());

        return studentResponseDto;

    }
}
