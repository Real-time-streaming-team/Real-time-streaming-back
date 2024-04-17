package com.example.realtimestreaming.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.header.ReferrerPolicyServerHttpHeadersWriter;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                // 다른 보안 설정 추가 가능
                .headers(headers ->
                    headers.referrerPolicy(referrerPolicy -> referrerPolicy.policy(ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy.NO_REFERRER_WHEN_DOWNGRADE))
                );
        return http.build();
    }
}
