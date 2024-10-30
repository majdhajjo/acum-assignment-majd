package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.domain.Student;
import org.example.domain.StudentRequest;
import org.example.exceptions.InvalidStudentRequestException;
import org.example.exceptions.StudentNotExistException;
import org.example.repository.StudentRepository;
import org.example.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public Student getStudentByID(Integer studentID) {
        log.debug("start get student by id. studentId: " + studentID);
        return studentRepository.findByIdWithCourses(studentID)
                .orElseThrow(() -> new StudentNotExistException("Student not found. id: " + studentID));
    }

    @Override
    public Integer addStudent(StudentRequest request) {
        log.debug("start add new student . request: " + request);
        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .address(request.getAddress())
                .phone(request.getPhone())
                .major(request.getMajor())
                .enrollmentDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        Student res;
        try {
            res = studentRepository.save(student);
        } catch (DataIntegrityViolationException ex) {
            log.error("failed to add student. request: " + request);
            throw new InvalidStudentRequestException("bad request: violation exception");
        }
        return res.getStudentID();
    }

    @Override
    public Integer updateStudentInfo(Integer studentID, StudentRequest request) {
        log.debug("start update student info. request: " + request);
        try {
            studentRepository.updateStudent(studentID,
                    request.getName(),
                    request.getEmail(),
                    request.getAddress(),
                    request.getPhone(),
                    request.getMajor());
        } catch (DataIntegrityViolationException ex) {
            log.error("failed to update info. request: " + request);
            throw new InvalidStudentRequestException("bad request: violation exception");
        }
        return studentID;
    }


    @Override
    public List<Student> getAllStudents() {
        log.debug("start get all students");
        return studentRepository.findAll();
    }
}
