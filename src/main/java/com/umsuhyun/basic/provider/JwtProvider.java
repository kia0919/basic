package com.umsuhyun.basic.provider;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

//! JWT :
// - Json Web Token, RFC 7519 표준에 정의된 json 형식의 문자열을 포함하고 있는 토큰
// - 인증 및 인가에 사용
// - 암호화가 되어 있어 클라이언트와 서버 간의 안전한 데이터 교환을 할 수 있음
// - 헤더, 페이로드, 서명으로 구성된다
// - 헤더 : 토큰의 유형(일반적으로 jwt)과 암호화 알고리즘이 포함되어있음
// - 페이로드 : 클라이언트가 혹은 서버가 상대방에게 전달할 데이터가 포함되어있음(작성자, 접근주체의 정보, 작성시간, 만료시간)
// -서명 : 헤더와 페이로드를 합쳐서 인코딩하고 지정한 비밀키로 암호화한 데이터
@Component
// JwtProvider 클래스 생성
public class JwtProvider {

    // JWT 암호화에 사용되는 비밀키는 보안 관리가 되어야 함
    // 코드에 직접적으로 비밀키를 작성하는 것은 보안상 좋지 않음
    //! 해결책 
    // 1. application.properties / application.yaml 에 등록
    // - application.properties 혹은 application.yaml에 비밀키를 작성
    // - @Value()를 이용하여 데이터를 가져옴
    // - 주의사항 : application.properties / application.yaml을 .gitignore에 등록해야 함.
    @Value("${jwt.sercret-key}")
    private String secretKey;

    //! JWT 생성
    // Jwt 생성 메서드
    public String create(String principle) {
        // 만료시간 생성
        // Instant.now():현재시간, plus(4, ChronoUnit.HOURS): 4시간 후를 만료시간.
        Date expiredDate = Date.from(Instant.now().plus(4, ChronoUnit.HOURS));
        // 비밀키 생성
        // StandardCharsets를 사용하여 UTF-8 문자 인코딩으로 키를 설정.
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        // JWT 생성
        // Jwts.builder()를 호출하여 JWT 빌더를 생성
        String jwt = Jwts.builder()
                // 서명 (서명에 사용할 비밀키, 서명에 사용할 암호화 알고리즘)
                // Keys.hmacShaKeyFor를 사용하여 HMAC-SHA256 알고리즘을 사용하는 키를 생성.
                .signWith(key, SignatureAlgorithm.HS256)
                // 페이로드 
                // "작성자"
                .setSubject(principle)
                // 생성시간
                .setIssuedAt(new Date())
                // 만료시간
                .setExpiration(expiredDate)
                // 위의 내용을 압축(인코딩)
                // .compact 호출하여 Jwt 생성, 반환
                .compact();

                return jwt;      
    }

    public String validation(String jwt) {
        
        // jwt 검증 결과로 나타나는 페이로드가 저장될 변수
        Claims claims = null;
        // 비밀키 생성
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        try {
            // 비밀키로 jwt 복호화 작업, 예외 발생 시
            claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        } catch(Exception exception){
            exception.printStackTrace();
            return null;
        }

        return claims.getSubject();

    }

}
