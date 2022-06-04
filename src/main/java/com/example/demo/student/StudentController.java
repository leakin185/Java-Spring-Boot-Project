package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student") // the api is version 1
public class StudentController {
    private final StudentService studentService;

    @Autowired // the above will be automatically instantiated and injected into the constructor for the following
            // methods to work
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
        // this.studentService = new.StudentService // avoid this and use dependency injection as much as possible
    }

    @GetMapping
    public List<Student> GetStudents() {
        return studentService.GetStudents();
    }
    // end point that returns an array of students
    // when we open http://localhost:8080/api/v1/student, it will show a list of students that is passed into the StudentController
    // things are more organised now

    // with this, we are using dependency injection and have split things into layers

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);{
        };
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }
}
