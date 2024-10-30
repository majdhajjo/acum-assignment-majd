package org.example.web;

import lombok.AllArgsConstructor;
import org.example.domain.CourseResponse;
import org.example.domain.ErrMsg;
import org.example.domain.Student;
import org.example.domain.StudentRequest;
import org.example.exceptions.InvalidStudentRequestException;
import org.example.exceptions.StudentNotExistException;
import org.example.service.CourseService;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education-system")
@AllArgsConstructor
public class EducationSystemController {


    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    /**
     * first endpoint of getting student's data
     */
    @GetMapping("/student/{student_id}")
    public ResponseEntity<Object> getStudent(@PathVariable("student_id") Integer studentID) {
        try {
            Student student = studentService.getStudentByID(studentID);
            return ResponseEntity.ok(student);
        } catch (StudentNotExistException e) {
            return ResponseEntity.badRequest().body(ErrMsg.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * second endpoint of adding new student
     */
    @PostMapping("/new/student")
    public ResponseEntity<Object> addStudent(@RequestBody StudentRequest request) {
        try {
            Integer res = studentService.addStudent(request);
            return ResponseEntity.ok("new student has been added. studentId:" + res);
        } catch (InvalidStudentRequestException e) {
            return ResponseEntity.badRequest().body(ErrMsg.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ErrMsg.builder().message("Internal Server Error").build());
        }
    }

    /**
     * third endpoint of updating student's data
     */
    @PutMapping("/student/{student_id}/edit")
    public ResponseEntity<Object> editStudentInfo(@PathVariable("student_id") Integer studentID, @RequestBody StudentRequest request) {
        try {
            Integer res = studentService.updateStudentInfo(studentID, request);
            return ResponseEntity.ok("student info has been updated. studentId:" + res);
        } catch (InvalidStudentRequestException e) {
            return ResponseEntity.badRequest().body(ErrMsg.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ErrMsg.builder().message("Internal Server Error").build());
        }
    }

    /**
     * fourth endpoint of getting list of all students
     */
    @GetMapping("/student/all")
    public ResponseEntity<Object> getAllStudents() {
        try {
            List<Student> allStudents = studentService.getAllStudents();
            if (allStudents.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(allStudents);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    /**
     * fifth endpoint of getting all courses with amount of each course
     */
    @GetMapping("/courses")
    public ResponseEntity<Object> getAllCourses() {
        try {
            List<CourseResponse> allCourses = courseService.getAllCourses();
            if (allCourses.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(allCourses);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
