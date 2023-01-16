package com.example.java26.week3.rest.APIHW.pojo.dto;

import com.example.java26.week3.rest.APIHW.pojo.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StudentResponseDTO {
    private List<StudentDTO> studentList;

    @Data
    @AllArgsConstructor
    @Builder
    public static class StudentDTO {
        private int id;
        private String name;

        public StudentDTO(Student s) {
            this.id = s.getId();
            this.name = s.getName();
        }
    }
}
