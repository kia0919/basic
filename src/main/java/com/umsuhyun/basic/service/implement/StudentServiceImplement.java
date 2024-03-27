package com.umsuhyun.basic.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.umsuhyun.basic.Entity.StudentEntity;
import com.umsuhyun.basic.dto.request.student.PostStudentRequestDto;
import com.umsuhyun.basic.repository.StudentRepository;
import com.umsuhyun.basic.service.studentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImplement implements studentService {
    
    private final StudentRepository studentRepository;

    @Override
    public ResponseEntity<String>
    postStudent(PostStudentRequestDto dto) {

        StudentEntity studentEntity = new StudentEntity(dto);
        studentRepository.save(studentEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body("성공!");
    }

}
sda