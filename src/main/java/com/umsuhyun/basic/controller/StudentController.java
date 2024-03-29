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
import com.umsuhyun.basic.service.StudentService;

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

    // DELETE       ** @RequestMapping("/student")으로 http://localhost:4000/student/에서  @DeleteMapping("/{studentNumber}")로 인해
                            // 뒤에 삭제할 넘버를 적으면된다. 테이블에 1번 학생이 있으면 http://localhost:4000/student/1  URL 보내면 1번 학생이 삭제된다.
    @DeleteMapping("/{studentNumber}")
    public ResponseEntity<String> deleteStudent(
        @PathVariable("studentNumber") Integer studentNumber
    ) {
        ResponseEntity<String> response = studentService.deleteStudent(studentNumber);
        return response;
    }

}
