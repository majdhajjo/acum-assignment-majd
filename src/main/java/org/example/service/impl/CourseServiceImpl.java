package org.example.service.impl;


import lombok.AllArgsConstructor;
import org.example.domain.CourseResponse;
import org.example.repository.CourseRepository;
import org.example.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public List<CourseResponse> getAllCourses() {
        log.debug("start get all courses");
        return courseRepository.findCoursesWithStudentCount();
    }
}
