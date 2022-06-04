package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// @Component // indicating that it is a spring bean
@Service  // component/service is the same, but we use service for semantics (clearer understanding)
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

//    public List<Student> GetStudents() {
//        return List.of(new Student(
//                1L, "Mariam", 21, LocalDate.of(2000, Month.DECEMBER, 6), "xxx@gmail.com"
//        ));
//    } do not need this after using the data access layer via repository

    public List<Student> GetStudents() {
        return studentRepository.findAll();
    }


    //data service layer

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
        System.out.println(student);

    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(("Student with id " + studentId + " does not exist"));

        }
        studentRepository.deleteById((studentId));
    }

    @Transactional // we are not doing queries
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById((studentId)).orElseThrow(() -> new IllegalStateException(
                "student with id " + studentId + "does not exist"
        ));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail((email));
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
