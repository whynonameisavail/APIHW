package com.example.java26.week3.rest.APIHW.controller;

import com.example.java26.week3.rest.APIHW.exception.ResourceNotFoundException;
import com.example.java26.week3.rest.APIHW.pojo.dto.StudentResponseDTO;
import com.example.java26.week3.rest.APIHW.pojo.dto.StudentResponseDTO.*;
import com.example.java26.week3.rest.APIHW.pojo.entity.Student;
import com.example.java26.week3.rest.APIHW.repository.StudentRepository;
import com.example.java26.week3.rest.APIHW.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;
    @Autowired
    public StudentController(@Qualifier("studentService") StudentService ss) {
        this.service = ss;
    }

    @GetMapping
    public ResponseEntity<StudentResponseDTO> getAll(@RequestParam(required = false) String name) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Student s) {
        return new ResponseEntity<>(service.insert(s), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Student s, @PathVariable int id) {
        return new ResponseEntity<>(service.update(s, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
    }

}
