package com.umsuhyun.basic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsuhyun.basic.dto.request.student.PatchStudentRequestDto;
import com.umsuhyun.basic.dto.request.student.PostStudentRequestDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    
    // CREATE
    @PostMapping("/")
    public ResponseEntity<String> postStudent (  // 제너릭에는 데이터 타입이 전달되어야 함 
        @RequestBody @Valid PostStudentRequestDto requestBody
    ) {
        ResponseEntity<String> response = studentService.postStudent(requestBody);
        return response;
    }     

    // UPDATE
    @PatchMapping("/")
    public ResponseEntity<String> patchStudent (
        @RequestBody @Valid PatchStudentRequestDto requestBody  //@Valid: 유효성 검사
    )      //String으로 반환
       {
        ResponseEntity<String> response = studentService.patchStudent(requestBody);
        return response;
    }

    // DELETE
    @DeleteMapping("/{studentNumber}")
    public ResponseEntity<String> deleteStudent(
        @PathVariable("studentNumber") Integer studentNumber
    ) {
        ResponseEntity<String> response = studentService.deleteStudent(studentNumber);
        return response;
    }

}
