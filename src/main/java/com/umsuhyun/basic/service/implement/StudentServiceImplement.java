package com.umsuhyun.basic.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.umsuhyun.basic.Entity.StudentEntity;
import com.umsuhyun.basic.dto.request.student.PatchStudentRequestDto;
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

        // CREATE(SQL: INSERT)
        // 1. Entity 클래스의 인스턴스 생성
        // 2. 생성한 인스턴스를 repository.save() 메서드로 저장
        StudentEntity studentEntity = new StudentEntity(dto);
        // save() : 저장 및 수정 ( 덮어쓰기 )
        studentRepository.save(studentEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body("성공!");
    }

    @Override
    public ResponseEntity<String> patchStudent(PatchStudentRequestDto dto) {
    
    Integer studentNumber = dto.getStudentNumber();
    String address = dto.getAddress();
    // 1.student class로 접근 (StudentRespository 사용)
    StudentEntity studentEntity = studentRepository.
    // 2.dto.studentNumber에 해당하는 instance를 검색
    findById(studentNumber).get();
    // 3.검색된 instance의 address값을 dto.address로 변경
    studentEntity.setAddress(address);
     }

}

/*Update student
SET address = dto.address
WHERE student_number = dto.studentNumber;

1.student 테이블로 접근
2.dto.studentNumber에 해당하는 record를 검색
3.검색된 record의 address값을 dto.address로 변경

----- 객체지향프로그맹 언어의 class == RDBMS의 table과 같다.-----
----- 객체지향프로그맹 언어의 instance == RDBMS의 record와 같다.-----

1.student class로 접근
2.dto.studentNumber에 해당하는 instance를 검색
3.검색된 instance의 address값을 dto.address로 변경

*/