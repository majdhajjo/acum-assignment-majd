package org.example.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "lectures")
public class Lecture {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    @Column(name = "lecture_id", updatable = false, nullable = false)
    private UUID lectureID;
    private String lectureName;
    private UUID courseID;
    private String study;
    private LocalTime lectureTime;
    private String dayOfWeek;
}
