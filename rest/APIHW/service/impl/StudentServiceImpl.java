package com.example.java26.week3.rest.APIHW.service.impl;
import com.example.java26.week3.rest.APIHW.exception.ResourceNotFoundException;
import com.example.java26.week3.rest.APIHW.pojo.dto.StudentResponseDTO;
import static com.example.java26.week3.rest.APIHW.pojo.dto.StudentResponseDTO.*;
import com.example.java26.week3.rest.APIHW.pojo.entity.Student;
import com.example.java26.week3.rest.APIHW.repository.StudentRepository;
import com.example.java26.week3.rest.APIHW.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentServiceImpl(@Qualifier("studentRepository") StudentRepository sr) {
        this.repository = sr;
    }

    @Override
    public StudentResponseDTO getAll() throws ResourceNotFoundException{
            Collection<Student> studentCollection = repository.getAll();
            List<StudentDTO> studentDTOS = studentCollection
                    .stream()
                    .map(s -> new StudentDTO(s))
                    .collect(Collectors.toList());
            return new StudentResponseDTO(studentDTOS);
    }

    @Override
    public StudentDTO getById(int id) throws ResourceNotFoundException {
        Student student = repository.getById(id);
        return new StudentDTO(student);
    }

    @Override
    public String insert(Student s) {
        return repository.insert(s);
    }

    @Override
    public String update(Student s, int id) {
        return repository.update(s, id);
    }

    @Override
    public String deleteById(int id) {
        return repository.deleteById(id);
    }
}
