package com.example.realtimestreaming.Config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootConfiguration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {

        //CorsConfiguration 개체 생성 후 설정 추가
        CorsConfiguration config = new CorsConfiguration();
        //내보낼 원본 도메인 설정
        config.addAllowedOrigin("*");
        //원본 요청 헤더 정보 내보내기
        config.addAllowedHeader("*");
        //header 의 노출 정보
        config.addExposedHeader("*");
        //허용할 요청 항목들
        config.addAllowedMethod("GET");     //get
        config.addAllowedMethod("PUT");     //put
        config.addAllowedMethod("POST");    //post
        config.addAllowedMethod("DELETE");  //delete
        //corsConfig.addAllowedMethod("*");     //모두허용

        // Cookie 전송여부
        config.setAllowCredentials(true);

        //2. 매핑 경로 추가
        UrlBasedCorsConfigurationSource corsConfigurationSource =
                new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", config);

        return new CorsFilter(corsConfigurationSource);
    }
}