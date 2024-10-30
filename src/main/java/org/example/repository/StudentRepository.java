package org.example.repository;

import org.example.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.courses WHERE s.studentID = :studentID")
    Optional<Student> findByIdWithCourses(@Param("studentID") Integer studentID);

    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.name = :name, s.email = :email, s.address = :address, s.phone = :phone, s.major = :major WHERE s.studentID = :studentID")
    int updateStudent(@Param("studentID") Integer studentID, @Param("name") String name, @Param("email") String email,
                      @Param("address") String address, @Param("phone") String phone, @Param("major") String major);

}
