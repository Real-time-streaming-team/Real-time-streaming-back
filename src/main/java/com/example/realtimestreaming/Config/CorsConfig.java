package com.example.realtimestreaming.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // 여러 개의 출처를 허용하도록 설정
        config.setAllowedOrigins(Arrays.asList(
                "http://158.247.240.142:3000/",
                "http://124.46.240.195:63342"
        ));

        // 허용할 HTTP 메서드 설정
        config.addAllowedMethod("*");
        // 허용할 헤더 설정
        config.addAllowedHeader("*");
        // 인증 정보 허용 설정
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}