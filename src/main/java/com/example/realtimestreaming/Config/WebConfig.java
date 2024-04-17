package com.example.realtimestreaming.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        //  Set routes that allow cross domain routing
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
//                .allowCredentials(true)
                .allowedMethods("*")
                .maxAge(3600);
    }
}