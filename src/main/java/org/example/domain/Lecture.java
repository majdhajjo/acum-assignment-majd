package org.example.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Builder
@Entity
@Table(name = "lectures", schema = "education_schema")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Integer lectureID;
    private String lectureName;
    private Integer courseID;
    private String study;
    private LocalTime lectureTime;
    private String dayOfWeek;
}
