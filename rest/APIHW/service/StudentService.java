package com.example.java26.week3.rest.APIHW.service;
import com.example.java26.week3.rest.APIHW.pojo.dto.StudentResponseDTO;
import com.example.java26.week3.rest.APIHW.pojo.dto.StudentResponseDTO.*;
import com.example.java26.week3.rest.APIHW.pojo.entity.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    StudentResponseDTO getAll();
    StudentDTO getById(int id);
    String insert(Student s);
    String update(Student s, int id);
    String deleteById(int id);

}
