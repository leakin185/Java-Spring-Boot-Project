package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// data access layer
// repository to work on type Student with unique id of type Long
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    // SELECT * FROM student WHERE email = ? (meaning for below)
    @Query("SELECT s FROM Student s WHERE s.email = ?1") //jbql
    Optional<Student> findStudentByEmail(String email);
}
