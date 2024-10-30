package org.example.service;

import org.example.domain.Course;
import org.example.domain.CourseResponse;

import java.util.List;

public interface CourseService {
    List<CourseResponse> getAllCourses();
}
