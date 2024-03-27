package com.umsuhyun.basic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    
    @PostMapping("/")
    public ResponseEntity<?> postStudent () {       // 제너릭에는 데이터 타입이 전달되어야 함 
        return null;
    }     

}
