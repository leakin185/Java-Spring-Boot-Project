package com.example.demo.student;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student mariam = new Student(
                    1L, "Mariam", LocalDate.of(2000, Month.DECEMBER, 6), "xxx@gmail.com"
            );
            Student alex = new Student(
                    2L, "Alex", LocalDate.of(2008, Month.DECEMBER, 9), "xxy@gmail.com"
            ); // save these students into the database
            repository.saveAll(
                    List.of(mariam, alex)
            ); // they should have different IDs when inserting
        };
    }
}
