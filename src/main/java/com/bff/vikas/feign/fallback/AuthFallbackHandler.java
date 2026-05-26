package com.bff.vikas.feign.fallback;

import org.springframework.stereotype.Component;

import com.bff.vikas.feign.dto.response.LoginResponse;
import com.bff.vikas.feign.dto.response.PasswordResetResponse;
import com.bff.vikas.feign.dto.response.UserProfileResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Class      : AuthFallbackHandler
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 20, 2026
 * Version    : 1.0
 */
@Component
@Slf4j
public class AuthFallbackHandler {

	/**
	 * User Profile Fallback 'message' field used for error text instead of
	 * 'fullName'
	 */
	public UserProfileResponse profileFallback(Throwable ex) {
		log.error("Profile Service Fallback triggered: {}", ex.getMessage());
		return UserProfileResponse.builder().status("SERVICE_UNAVAILABLE")
				.message("User profile service is temporarily busy. Please try later.")
				.fullName(null)
				.build();
	}

	/**
	 * Login/Register Fallback
	 */
	public LoginResponse authFallback(Throwable ex) {
		log.error("Auth Service Fallback triggered: {}", ex.getMessage());
		return LoginResponse.builder()
				.message("Authentication service is currently unavailable. Please try after some time.").build();
	}

	/**
	 * Generic Fallback for simple String responses (OTP, Lock/Unlock)
	 */
	public String stringFallback(Throwable ex) {
		log.error("Operation Fallback triggered: {}", ex.getMessage());
		return "Backend service is not responding. Action failed.";
	}

	/**
	 * Password Management Fallback
	 */
	public PasswordResetResponse passwordResetFallback(Throwable ex) {
		log.error("Password Reset Fallback triggered: {}", ex.getMessage());
		return PasswordResetResponse.builder()
				.status("FAILURE")
				.message("Unable to process password request at this moment.")
				.build();
	}
}
