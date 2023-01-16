package com.example.java26.week3.rest.APIHW.repository;

import com.example.java26.week3.rest.APIHW.pojo.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StudentRepository {
    String insert(Student s);
    String update(Student s, int id);
    Student getById(int id);
    Collection<Student> getAll();
    String deleteById(int id);
}
