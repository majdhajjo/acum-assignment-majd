package org.example.service.impl;

import org.example.domain.Student;
import org.example.domain.StudentRequest;
import org.example.exceptions.InvalidStudentRequestException;
import org.example.exceptions.StudentNotExistException;
import org.example.repository.StudentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

    private StudentServiceImpl classUnderTest;
    @Mock
    private StudentRepository studentRepositoryMock;

    @Before
    public void setUp() {
        classUnderTest = new StudentServiceImpl(studentRepositoryMock);
    }

    @Test
    public void getStudentByID() {
        Integer studentID = 1;
        Student expected = Student.builder().name("test1").email("email").build();
        Mockito.when(studentRepositoryMock.findByIdWithCourses(studentID)).thenReturn(Optional.ofNullable(expected));
        Student actual = classUnderTest.getStudentByID(1);
        Assert.assertEquals(expected, actual);

    }

    @Test(expected = StudentNotExistException.class)
    public void getStudentByID_notFound() {
        Integer studentID = 1;
        Mockito.when(studentRepositoryMock.findByIdWithCourses(studentID)).thenReturn(Optional.empty());
        classUnderTest.getStudentByID(1);
    }

    @Test
    public void addStudent() {
        StudentRequest request = StudentRequest.builder()
                .name("test").phone("123").major("major").address("ddd").email("email").build();
        Integer expected = 1;
        Student student = Student.builder().studentID(expected).name("test").email("email").build();
        Mockito.when(studentRepositoryMock.save(Mockito.any())).thenReturn(student);
        Integer actual = classUnderTest.addStudent(request);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = InvalidStudentRequestException.class)
    public void addStudent_invalidRequest() {
        StudentRequest request = StudentRequest.builder()
                .name("test").phone("123").major("major").address("ddd").email("email").build();
        Integer expected = 1;
        Mockito.when(studentRepositoryMock.save(Mockito.any())).thenThrow(DataIntegrityViolationException.class);
        classUnderTest.addStudent(request);
    }


    @Test
    public void updateStudentInfo() {
        StudentRequest request = StudentRequest.builder()
                .name("test").phone("123").major("major").address("ddd").email("email").build();
        Integer expected = 1;
        Mockito.when(studentRepositoryMock
                .updateStudent(expected, request.getName(), request.getEmail(), request.getAddress(),
                        request.getPhone(), request.getMajor())).thenReturn(1);
        Integer actual = classUnderTest.updateStudentInfo(expected, request);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = InvalidStudentRequestException.class)
    public void updateStudentInfo_invalid() {
        StudentRequest request = StudentRequest.builder()
                .name("test").phone("123").major("major").address("ddd").email("email").build();
        Integer expected = 1;
        Mockito.when(studentRepositoryMock
                .updateStudent(expected, request.getName(), request.getEmail(), request.getAddress(),
                        request.getPhone(), request.getMajor())).thenThrow(DataIntegrityViolationException.class);
        classUnderTest.updateStudentInfo(expected, request);
    }

    @Test
    public void getAllStudents() {
        List<Student> expected = Collections.singletonList(Student.builder().name("test1").email("email").build());
        Mockito.when(studentRepositoryMock.findAll()).thenReturn(expected);
        List<Student> actual = classUnderTest.getAllStudents();
        Assert.assertEquals(expected, actual);
    }
}