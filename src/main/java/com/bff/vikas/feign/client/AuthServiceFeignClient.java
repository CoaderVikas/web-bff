package com.bff.vikas.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.bff.vikas.feign.dto.LoginRequest;
import com.bff.vikas.feign.dto.LoginResponse;
import com.bff.vikas.feign.dto.PasswordResetRequest;
import com.bff.vikas.feign.dto.PasswordResetResponse;
import com.bff.vikas.feign.dto.RegisterRequest;
import com.bff.vikas.feign.dto.UpdateProfileRequest;
import com.bff.vikas.feign.dto.UserProfileResponse;


/**
 * Class      : AuthServiceFeignClient
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 15, 2026
 * Version    : 1.0
 */

@FeignClient(name = "AUTH-SERVICE",path = "/rent-hub/auth")
public interface AuthServiceFeignClient {
	
	/**
	 * profile controller API
	 */
	@GetMapping("/profile/me")
	UserProfileResponse getMyProfile(@RequestHeader("Authorization") String token);

	@PutMapping("/profile/me")
	UserProfileResponse updateProfile(@RequestHeader("Authorization") String token,@RequestBody UpdateProfileRequest request);

	// Forgot Password (OTP generate)
	@PostMapping("/password/forgot")
	String generateOtp(@RequestParam("username") String username);

	// Reset Password
	@PostMapping("/password/reset")
	PasswordResetResponse resetPassword(@RequestBody PasswordResetRequest request);
	
	// Register API
	@PostMapping("/auth/register")
	LoginResponse register(@RequestBody RegisterRequest request);

	// Login API
	@PostMapping("/auth/login")
	LoginResponse login(@RequestBody LoginRequest request);
	
	// Lock user
	@PutMapping("/rent-hub/auth/admin/users/{id}/lock")
	String lockUser(@RequestHeader("Authorization") String token, @PathVariable("id") Long id);

	// Unlock user
	@PutMapping("/rent-hub/auth/admin/users/{id}/unlock")
	String unlockUser(@RequestHeader("Authorization") String token, @PathVariable("id") Long id);

	// Enable / Disable user
	@PutMapping("/rent-hub/auth/admin/users/{id}/status")
	String updateUserStatus(@RequestHeader("Authorization") String token, @PathVariable("id") Long id,
			@RequestParam("enabled") boolean enabled);

	// Update role
	@PutMapping("/rent-hub/auth/admin/users/{id}/role")
	String updateUserRole(@RequestHeader("Authorization") String token, @PathVariable("id") Long id,
			@RequestParam("role") String role);

	// Get all users (pagination)
	/*@GetMapping("/rent-hub/auth/admin/users")
	Page<AdminUserResponse> getAllUsers(@RequestHeader("Authorization") String token, @RequestParam("page") int page,
			@RequestParam("size") int size);*/
}
