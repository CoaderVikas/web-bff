package com.bff.vikas.feign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.bff.vikas.feign.client.AuthServiceFeignClient;
import com.bff.vikas.feign.dto.ChangePasswordRequest;
import com.bff.vikas.feign.dto.ChangePasswordResponse;
import com.bff.vikas.feign.dto.LoginRequest;
import com.bff.vikas.feign.dto.LoginResponse;
import com.bff.vikas.feign.dto.PaginatedUserResponse;
import com.bff.vikas.feign.dto.PasswordResetRequest;
import com.bff.vikas.feign.dto.PasswordResetResponse;
import com.bff.vikas.feign.dto.RefreshRequest;
import com.bff.vikas.feign.dto.RegisterRequest;
import com.bff.vikas.feign.dto.UpdateProfileRequest;
import com.bff.vikas.feign.dto.UserProfileResponse;
import com.bff.vikas.feign.fallback.AuthFallbackHandler;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

	@Autowired
	private AuthServiceFeignClient feignClient;

	@Autowired
	private AuthFallbackHandler fallbackHandler;

	private static final String AUTH_SERVICE = "default";

	// --- PROFILE METHODS ---

	@Retry(name = AUTH_SERVICE)
	@CircuitBreaker(name = AUTH_SERVICE, fallbackMethod = "getProfileFallback")
	public UserProfileResponse getProfile(String token) {
		log.info("**** Get Profile calling...****");
		return feignClient.getMyProfile(token);
	}

	@Retry(name = AUTH_SERVICE)
	@CircuitBreaker(name = AUTH_SERVICE, fallbackMethod = "updateProfileFallback")
	public UserProfileResponse updateProfile(String token, UpdateProfileRequest request) {
		return feignClient.updateProfile(token, request);
	}

	// --- AUTH METHODS ---

	@Retry(name = AUTH_SERVICE)
	@CircuitBreaker(name = AUTH_SERVICE, fallbackMethod = "registerFallback")
	public LoginResponse register(RegisterRequest request) {
		return feignClient.register(request);
	}

	//@Retry(name = AUTH_SERVICE)
	//@CircuitBreaker(name = AUTH_SERVICE, fallbackMethod = "loginFallback")
	public LoginResponse login(LoginRequest request) {
		return feignClient.login(request);
	}

	// --- PASSWORD & ADMIN ---

	//@Retry(name = AUTH_SERVICE)
	//@CircuitBreaker(name = AUTH_SERVICE, fallbackMethod = "otpFallback")
	public String generateOtp(String username) {
		return feignClient.generateOtp(username);
	}

	@Retry(name = AUTH_SERVICE)
	@CircuitBreaker(name = AUTH_SERVICE, fallbackMethod = "genericStringFallback")
	public String lockUser(String token, Long id) {
		return feignClient.lockUser(token, id);
	}

	//@Retry(name = AUTH_SERVICE)
	//@CircuitBreaker(name = AUTH_SERVICE, fallbackMethod = "pwResetFallback")
	public PasswordResetResponse resetPassword(PasswordResetRequest request) {
		return feignClient.resetPassword(request);
	}

	public String logout(String token,RefreshRequest request) {
		log.info("Processing logout for token type: REFRESH");
		return feignClient.logout(token,request);
	}
	
	public ChangePasswordResponse changePassword(ChangePasswordRequest request,String  token) {
		log.info("Processing changePassword .....");
		String authHeader = token.startsWith("Bearer ") ? token : "Bearer " + token;
		return feignClient.changePassword(request, authHeader);
	}
	
	public String unlockUser(String token, Long id) {
		log.info("Requesting to unlock user with ID: {}", id);
		return feignClient.unlockUser(token, id);
	}

	
	public String updateUserStatus(String token, Long id, boolean enabled) {
		log.info("Updating status for user ID: {} to enabled={}", id, enabled);
		return feignClient.updateUserStatus(token, id, enabled);
	}

	
	public String updateUserRole(String token, Long id, String role) {
		log.info("Updating role for user ID: {} to {}", id, role);
		return feignClient.updateUserRole(token, id, role);
	}
	
	public PaginatedUserResponse getAllUsers(String token,int page,int size) {
		log.info("fetching User for page ID: {} size {}", page, size);
		return feignClient.getAllUsers(token,page, size);
	}
	
	// --- PROXY FALLBACK METHODS (Directing to Handler) ---
	public UserProfileResponse getProfileFallback(String t, Throwable e) {
		return fallbackHandler.profileFallback(e);
	}

	public UserProfileResponse updateProfileFallback(String t, UpdateProfileRequest r, Throwable e) {
		return fallbackHandler.profileFallback(e);
	}

	public LoginResponse registerFallback(RegisterRequest r, Throwable e) {
		return fallbackHandler.authFallback(e);
	}

	public LoginResponse loginFallback(LoginRequest r, Throwable e) {
		return fallbackHandler.authFallback(e);
	}

	public String otpFallback(String u, Throwable e) {
		return fallbackHandler.stringFallback(e);
	}

	public String genericStringFallback(String t, Long i, Throwable e) {
		return fallbackHandler.stringFallback(e);
	}

	public PasswordResetResponse pwResetFallback(PasswordResetRequest r, Throwable e) {
		return fallbackHandler.passwordResetFallback(e);
	}
}