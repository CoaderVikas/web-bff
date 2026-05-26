package com.bff.vikas.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.form.spring.SpringFormEncoder;

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

	@Bean
	public Encoder feignFormEncoder() {
		return new SpringFormEncoder(new SpringEncoder(() -> new HttpMessageConverters()));
	}

	@Bean
	public RequestInterceptor requestInterceptor() {

		return requestTemplate -> {

			ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

			if (attrs != null) {

				String token = attrs.getRequest().getHeader("Authorization");

				if (token != null && !token.isEmpty()) {
					requestTemplate.header("Authorization", token);
				}
			}
		};
	}
}
