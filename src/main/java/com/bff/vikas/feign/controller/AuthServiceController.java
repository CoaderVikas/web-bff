package com.bff.vikas.feign.controller;


import org.springframework.http.HttpStatus;
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

import com.bff.vikas.feign.client.AuthServiceFeignClient;
import com.bff.vikas.feign.dto.LoginRequest;
import com.bff.vikas.feign.dto.LoginResponse;
import com.bff.vikas.feign.dto.PasswordResetRequest;
import com.bff.vikas.feign.dto.PasswordResetResponse;
import com.bff.vikas.feign.dto.RegisterRequest;
import com.bff.vikas.feign.dto.UpdateProfileRequest;
import com.bff.vikas.feign.dto.UserProfileResponse;

import lombok.AllArgsConstructor;


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
public class AuthServiceController {
	
	private final AuthServiceFeignClient authServiceFeignClient;
	
	@GetMapping("/profile")
	public ResponseEntity<UserProfileResponse> getMyProfile(@RequestHeader("Authorization") String token) {
		UserProfileResponse myProfile = authServiceFeignClient.getMyProfile(token);
		return ResponseEntity.ok(myProfile);
	}
	
	// Update Profile
	@PutMapping("/profile")
	public ResponseEntity<?> updateProfile(@RequestHeader("Authorization") String token,
			@RequestBody UpdateProfileRequest request) {

		try {
			UserProfileResponse response = authServiceFeignClient.updateProfile(token, request);

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("PROFILE SERVICE DOWN");
		}
	}

	// Forgot Password (OTP generate)
	@PostMapping("/password/forgot")
	public ResponseEntity<String> generateOtp(@RequestParam("username") String username) {
		return ResponseEntity.ok(authServiceFeignClient.generateOtp(username));
	}

	// Reset Password
	@PostMapping("/password/reset")
	public ResponseEntity<PasswordResetResponse> resetPassword(@RequestBody PasswordResetRequest request) {
		return ResponseEntity.ok(authServiceFeignClient.resetPassword(request));
	}

	// Register API
	@PostMapping("/register")
	public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authServiceFeignClient.register(request));
	}

	// Login API
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authServiceFeignClient.login(request));
	}

	// Lock User (Admin)
	@PutMapping("/admin/users/{id}/lock")
	public ResponseEntity<String> lockUser(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) {
		return ResponseEntity.ok(authServiceFeignClient.lockUser(token, id));
	}

	// Unlock User (Admin)
	@PutMapping("/admin/users/{id}/unlock")
	public ResponseEntity<String> unlockUser(@RequestHeader("Authorization") String token,
			@PathVariable("id") Long id) {

		return ResponseEntity.ok(authServiceFeignClient.unlockUser(token, id));
	}

	// Update User Status (Enable/Disable)
	@PutMapping("/admin/users/{id}/status")
	public ResponseEntity<String> updateUserStatus(@RequestHeader("Authorization") String token,
			@PathVariable("id") Long id, @RequestParam("enabled") boolean enabled) {
		return ResponseEntity.ok(authServiceFeignClient.updateUserStatus(token, id, enabled));
	}

	// Update User Role
	@PutMapping("/admin/users/{id}/role")
	public ResponseEntity<String> updateUserRole(@RequestHeader("Authorization") String token,
			@PathVariable("id") Long id, @RequestParam("role") String role) {
		return ResponseEntity.ok(authServiceFeignClient.updateUserRole(token, id, role));
	}

}
