package com.bff.vikas.feign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.vikas.feign.client.AuthServiceFeignClient;
import com.bff.vikas.feign.dto.UserProfileResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

/**
 * Class      : AuthService
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 19, 2026
 * Version    : 1.0
 */
@Service
@Slf4j
public class AuthService {

	@Autowired
	private AuthServiceFeignClient feignClient;
	
	@Retry(name = "default")
	@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
	public UserProfileResponse getProfile(String token) {
		 log.info("Calling AUTH SERVICE...");
		return feignClient.getMyProfile(token);
	}

	public UserProfileResponse fallbackMethod(String token, Throwable ex) {
		log.info("fallbackMethod called .....");
		
		return UserProfileResponse
				.builder()
				.status("SERVICE_UNAVAILABLE")
				.fullName("Service Down - Try Later")
				.build();
	}

}
