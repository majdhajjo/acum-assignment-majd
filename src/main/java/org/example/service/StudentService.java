package org.example.service;

import org.example.domain.Student;
import org.example.domain.StudentRequest;

import java.util.List;

public interface StudentService {

    Student getStudentByID(Integer studentId);


    Integer addStudent(StudentRequest request);

    Integer updateStudentInfo(Integer studentID, StudentRequest request);

    List<Student> getAllStudents();
}
