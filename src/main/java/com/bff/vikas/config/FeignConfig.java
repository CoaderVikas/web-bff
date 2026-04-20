package com.bff.vikas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.ErrorDecoder;

/**
 * Class      : FeignConfig
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 20, 2026
 * Version    : 1.0
 */

@Configuration
public class FeignConfig {

    
    @Bean
    ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }
}
