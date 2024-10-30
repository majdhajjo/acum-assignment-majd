package org.example.repository;

import org.example.domain.Course;
import org.example.domain.CourseResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("SELECT new org.example.domain.CourseResponse(c.courseID, c.courseName, COUNT(sc.studentID)) " +
            "FROM Course c " +
            "LEFT JOIN c.students sc " +
            "GROUP BY c.courseID, c.courseName")
    List<CourseResponse> findCoursesWithStudentCount();
}
