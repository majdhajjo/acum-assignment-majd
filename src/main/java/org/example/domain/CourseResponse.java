package org.example.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CourseResponse {
    private Integer courseID;
    private String courseName;
    private Long studentsAmount;
}
