package com.umsuhyun.basic.provider;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;

import com.umsuhyun.basic.entity.CustomOAuth2User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


//! OAuth2에 대한 모든 처리 과정이 성공적으로 끝났을 때 수행하는 메서드를 생성하기 위한 클래스
// - 반드시 SimpleUrlAuthenticationSuccessHandler 클래스 확장해야함
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements ServerAuthenticationSuccessHandler {

    private final JwtProvider JwtProvider;

    @Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
            
        // OAuth2UserServiceImplement에서 반환하는 OAuth2User객체를 받아옴
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        // OAuth2User 객체의 사용자 이름 가져옴
        String name = customOAuth2User.getName();
        // jwt 발급
        String token = JwtProvider.create(name);

        response.getWriter().write(token);

        }

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
    }


    }
    

