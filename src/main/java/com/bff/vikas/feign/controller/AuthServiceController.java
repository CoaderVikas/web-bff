package com.bff.vikas.feign.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bff.vikas.feign.dto.LoginRequest;
import com.bff.vikas.feign.dto.LoginResponse;
import com.bff.vikas.feign.dto.PasswordResetRequest;
import com.bff.vikas.feign.dto.PasswordResetResponse;
import com.bff.vikas.feign.dto.RefreshRequest;
import com.bff.vikas.feign.dto.RegisterRequest;
import com.bff.vikas.feign.dto.UpdateProfileRequest;
import com.bff.vikas.feign.dto.UserProfileResponse;
import com.bff.vikas.feign.service.AuthService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Class      : AuthServiceController
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 18, 2026
 * Version    : 1.0
 */
@RestController
@RequestMapping(value = "/api/v1/auth")
@AllArgsConstructor
@Slf4j
public class AuthServiceController {
	
	@Autowired
	private AuthService authService;
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	@GetMapping("/profile")
	public ResponseEntity<UserProfileResponse> getMyProfile(@RequestHeader("Authorization") String token) {
		log.info("In the block of Web_bff");
		UserProfileResponse myProfile = authService.getProfile(token);
		return ResponseEntity.ok(myProfile);
	}
	
	/**
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@PutMapping("/profile")
	public ResponseEntity<?> updateProfile(@RequestHeader("Authorization") String token,
			@RequestBody UpdateProfileRequest request) {
			UserProfileResponse response = authService.updateProfile(token, request);
			return ResponseEntity.ok(response);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authService.register(request));
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}
	
	/**
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestHeader("Authorization") String token,@RequestBody RefreshRequest request) {
		return ResponseEntity.ok(authService.logout(token,request));
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/reset-password")
	public ResponseEntity<PasswordResetResponse> resetPassword(@RequestBody PasswordResetRequest request) {
		return ResponseEntity.ok(authService.resetPassword(request));
	}
	
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	@PostMapping("/forgot-password")
	public ResponseEntity<String> generateOtp(@RequestParam("username") String username) {
		return ResponseEntity.ok(authService.generateOtp(username));
	}
	
	
	/**
	 * 
	 * @param token
	 * @param id
	 * @return
	 */
	@PutMapping("/admin/users/{id}/lock")
	public ResponseEntity<String> lockUser(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) {
		return ResponseEntity.ok(authService.lockUser(token, id));
	}

	/*
	/**
	 * 
	 * @param token
	 * @param id
	 * @return
	 */
	@PutMapping("/admin/users/{id}/unlock")
	public ResponseEntity<String> unlockUser(@RequestHeader("Authorization") String token,
			@PathVariable("id") Long id) {

		return ResponseEntity.ok(authService.unlockUser(token, id));
	}

	/**
	 * 
	 * @param token
	 * @param id
	 * @param enabled
	 * @return
	 */
	@PutMapping("/admin/users/{id}/status")
	public ResponseEntity<String> updateUserStatus(@RequestHeader("Authorization") String token,
			@PathVariable("id") Long id, @RequestParam("enabled") boolean enabled) {
		return ResponseEntity.ok(authService.updateUserStatus(token, id, enabled));
	}

	/**
	 * 
	 * @param token
	 * @param id
	 * @param role
	 * @return
	 */
	@PutMapping("/admin/users/{id}/role")
	public ResponseEntity<String> updateUserRole(@RequestHeader("Authorization") String token,
			@PathVariable("id") Long id, @RequestParam("role") String role) {
		return ResponseEntity.ok(authService.updateUserRole(token, id, role));
	}

}
