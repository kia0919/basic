package com.umsuhyun.basic.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umsuhyun.basic.service.BasicService;

import lombok.RequiredArgsConstructor;

// Controller 레이어 : 
// -  출입력 담당.
// - 클라이언트와 서버간의 접점
// - 클라이언트의 요청을 받고 해당 요청에 대한 응답을 클라이언트게 돌려주는 영역
// - 각 요청에 해당하는 URL 메서드를 작성하는 영역

// @RestController : JSON 형태의 Response Body를 반환하는 Controller 임을 명시
// @RestController = @Controller + @ResponseBody

@RestController // 이 클래스가 JSON 형식의 응답을 반환하는 컨트롤러임을 나타냅니다.
                            // json은 js객체 리터럴, 배열, 스칼라 데이터를 표현하는 텍스트 기반의 방식

// @RequestMapping() : HTTP 요청에 클래스와 메서드를 매핑하기 위한 어노테이션
// HTTP GET localhost:4000/main/**
// @RequestMapping(value="/main", method={RequestMethod.GET})

// HTTP localhost:4000/main/**
@RequestMapping("/main")  //:이 클래스의 모든 메서드가 "/main"경로에 매핑
// final로 선언된 필드 변수에 대해서 생성자를 자동으로 작성함

@RequiredArgsConstructor    // 필수 멤버변수들을 매개변수로 받는 생성자를 작성해주는 어노테이션 
public class BasicController {  // 어디서든 접근 가능한 클래스 선언 하는 것.

    // private BasicService service;

    // 의존성 역전 (DI) : 
    // - 해당 클래스에 필요한 의존성을 외부(클래스의 인스턴스를 생성하는 위치)에서 주입
    // - 생성자를 통한 의존성 주입, setter 메서드를 통한 의존성 주입, 필드를 통한 의존성 주입
    // - 생성자를 통한 의존성 주입을 spring에서 권장 (의존성이 주입되지 않은 경우가 존재할 수 없기 때문)
    // - spring framework에서 의존성 주입은 제어의 역전 (IoC)를 통해서 spring framework가 진행 (@Component를 사용해서 Spring Bean으로 등록된 Class에 한해서)

    // @Autowired : 등록된 Spring Bean을 제어의 역전을 통해서 의존성을 주입하는 방법
    // - 단, 생성자를 통한 의존성 주입에는 @Autowired를 생략해도 됨

    // @Autowired
    // public BasicController(BasicService service) {
    //     this.service = service;
    // }

    // 의존성 주입시 클래스로 직접 참조변수를 만들지 않고 인터페이스로 간접적으로 만드는 이유 : 
    // 고수준의 모듈에서 저수준의 모듈을 직접 참조하지 않고 추상화를 통해 간접 참조함으로 써 각 모듈간의 결합도를 낮춤 -> 코드의 재사용성 향상, 유지보수성 향상
    private final BasicService service; //
    
    // HTTP GET localhost:4000/main/
    // value="/"은 요청이 들어왔을 때 해당 메서드가 호출될 URL패턴 지정
    // method={RequestMethod.GET}: HTTP 메서드에 대한 매핑 정의, GET메서드에 대한 요청만 메서드 실행.
    @RequestMapping(value="/", method={RequestMethod.GET})
    // 클라이언트로부터 GET요청 처리후, 결과를 문자열로 반환. 메서드 이름인 getHello는 해당 메서드가 Hello 문자열을 반환.
    public String getHello() {
        return service.getHello(); // getHello메서드를 호출하여 작업 수행후에 결과를 반환한다. 결과는 문자열 형태로 반환되어 클라이언트에게 응답으로 전달.
    }

    // HTTP GET Method : 클라이언트가 서버로부터 데이터를 받기를 원할 때 사용하는 메서드
    
    // @GetMapping() : RequestMapping 기능을 GET Mathod에 한정시킨 것 (가독성 + 안정성)
    @GetMapping("/apple")   // GET 요청에 "/apple"로 GET요청이 수신되어 메서드가 호출되도록 지정
    // 결과값을 문자열 형태로 반환해준다.
    public String getApple () { 
        // apple데이터를 가져오는 서비스를 호출, 그 값을 반환.
        return service.getApple();
    }

    // HTTP POST Method : 클라이언트가 서버에 데이터를 작성하기 원할때 사용하는 메서드

    // @PostMapping() : RequestMapping 기능을 Post Method 한정시킨 것 (가독성 + 안정성),
    // 정보를 제출하는 용도
    @PostMapping("/apple")
    public String postApple() {
        return "Post Mapping 으로 만든 메서드";
    }

    // HTTP PUT Method : 클라이언트가 서버에 있는 리소스 전체를 수정하고 싶을 때 사용하는 메서드

    // @PutMapping() : RequestMapping 기능을 Put Method에 한정시킨 것 (가독성 + 안정성),
    // 서버의 데이터를 갱신하거나 새로운 리소스를 생성하는 데 사용
    @PutMapping("/apple")   
    public String putApple() {
        return "Put Mapping 으로 만든 메서드";
    }

    // HTTP PATCH Method : 클라이언트가 서버에 있는 리소스 일부를 수정하고 싶을 때 사용하는 메서드

    // @PatchMapping() : RequestMapping 기능을 Patch Method에 한정시킨 것 (가독성 + 안정성),
    // 리소스의 일부만 업데이트하는 데 사용. PUT과 달리 일부분만 수정하고 나머지는 유지.
    @PatchMapping("/apple")
    public String patchApple() {
        return "Patch Mapping 으로 만든 메서드";
    }

    // HTTP DELETE Method : 클라이언트가 서버에 있는 리소스를 삭제하고 싶을 때 사용하는 메서드
    
    // @DeleteMapping() : RequestMapping 기능을 Delete Method에 한정시킨 것 (가독성 + 안정성),
    // 특정 리소스 삭제하기 위함.
    @DeleteMapping("/apple")
    public String deleteApple() {
        return "Delete Mapping 으로 만든 메서드";
    }

    // Method + URL Pattern이 중복되면 런타임 중에 에러가 발생
    // @DeleteMapping("/apple")
    // public String deleteApple1() {
    //     return "Delete Mapping 으로 만든 메서드";
    // }

}