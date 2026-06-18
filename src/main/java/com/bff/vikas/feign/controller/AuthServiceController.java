package com.bff.vikas.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bff.vikas.feign.dto.request.ChangePasswordRequest;
import com.bff.vikas.feign.dto.request.LoginRequest;
import com.bff.vikas.feign.dto.request.RefreshRequest;
import com.bff.vikas.feign.dto.request.RegisterRequest;
import com.bff.vikas.feign.dto.request.UpdateProfileRequest;
import com.bff.vikas.feign.dto.response.*;
import com.bff.vikas.feign.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Class      : AuthServiceController
 * Description: BFF Controller to handle authentication and user management operations.
 * Author     : Vikas Yadav
 * Created On : Apr 18, 2026
 * Version    : 1.1
 */
@RestController
//@RequestMapping(value = "/rent-hub/api/v1/auth")
@RequestMapping(value = "/auth")
@AllArgsConstructor
@Slf4j
@Tag(name = "Auth Service APIs", description = "Endpoints for User Authentication, Profile Management, and Admin User Control")
public class AuthServiceController {
	
	@Autowired
	private AuthService authService;
	
	@GetMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get User Profile", description = "Fetches the profile details of the currently authenticated user using JWT token.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Profile details retrieved successfully"),
		@ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or expired token"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<UserProfileResponse> getMyProfile(@RequestHeader("Authorization") String token) {
		log.info("Fetching profile in Web_bff layer");
		UserProfileResponse myProfile = authService.getProfile(token);
		return ResponseEntity.ok(myProfile);
	}
	
	@PutMapping(value = "/profile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update Profile", description = "Updates the profile information for the authenticated user.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Profile updated successfully"),
		@ApiResponse(responseCode = "400", description = "Invalid request data"),
		@ApiResponse(responseCode = "401", description = "Unauthorized")
	})
	public ResponseEntity<?> updateProfile(@RequestHeader("Authorization") String token, @Valid @RequestBody UpdateProfileRequest request) {
		UserProfileResponse response = authService.updateProfile(token, request);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "User Registration", description = "Registers a new user and returns an authentication token.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "User registered successfully"),
		@ApiResponse(responseCode = "400", description = "Registration failed - User already exists or invalid data")
	})
	public ResponseEntity<LoginResponse> register(@Valid @RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "User Login", description = "Authenticates user credentials and returns a JWT access token.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Login successful"),
		@ApiResponse(responseCode = "401", description = "Invalid credentials")
	})
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}
	
	@PostMapping(value = "/logout")
	@Operation(summary = "User Logout", description = "Invalidates the user session and token.")
	public ResponseEntity<String> logout(@RequestHeader("Authorization") String token, @RequestBody RefreshRequest request) {
		return ResponseEntity.ok(authService.logout(token, request));
	}
	
	@PostMapping(value = "/refresh")
	@Operation(summary = "User Logout", description = "Invalidates the user session and token.")
	public ResponseEntity<LoginResponse> refresh(@RequestHeader("Authorization") String token, @RequestBody RefreshRequest request) {
		return ResponseEntity.ok(authService.refresh(token, request));
	}

	@PostMapping(value = "/password-reset", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Reset Password", description = "Resets the password using a valid reset token or OTP.")
	public ResponseEntity<PasswordResetResponse> resetPassword(@Valid @RequestBody PasswordResetRequest request) {
		return ResponseEntity.ok(authService.resetPassword(request));
	}
	
	@PostMapping("/password-forgot")
	@Operation(summary = "Forgot Password - Generate OTP", description = "Sends an OTP to the user's registered username/email for password recovery.")
	public ResponseEntity<String> generateOtp(@RequestParam("username") String username) {
		return ResponseEntity.ok(authService.generateOtp(username));
	}
	
	@PutMapping("/password-change")
	@Operation(summary = "Change Password", description = "Allows an authenticated user to change their current password.")
	public ResponseEntity<ChangePasswordResponse> changePassword(@Valid @RequestBody ChangePasswordRequest request, @RequestHeader("Authorization") String token) {
		return ResponseEntity.ok(authService.changePassword(request, token));
	}
	
	@PutMapping("/users/{id}/lock")
	@Operation(summary = "Lock User Account", description = "Admin only: Locks a user account to prevent login.")
	public ResponseEntity<String> lockUser(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) {
		return ResponseEntity.ok(authService.lockUser(token, id));
	}

	@PutMapping("/users/{id}/unlock")
	@Operation(summary = "Unlock User Account", description = "Admin only: Unlocks a previously locked user account.")
	public ResponseEntity<String> unlockUser(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) {
		return ResponseEntity.ok(authService.unlockUser(token, id));
	}

	@PutMapping("/users/{id}/status")
	@Operation(summary = "Update User Status", description = "Admin only: Enable or disable a user account.")
	public ResponseEntity<String> updateUserStatus(@RequestHeader("Authorization") String token, @PathVariable("id") Long id, @RequestParam("enabled") boolean enabled) {
		return ResponseEntity.ok(authService.updateUserStatus(token, id, enabled));
	}

	@PutMapping("/users/{id}/role")
	@Operation(summary = "Update User Role", description = "Admin only: Updates the role (e.g., ADMIN, USER) of a specific user.")
	public ResponseEntity<String> updateUserRole(@RequestHeader("Authorization") String token, @PathVariable("id") Long id, @RequestParam("role") String role) {
		return ResponseEntity.ok(authService.updateUserRole(token, id, role));
	}
	
	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get All Users", description = "Admin only: Retrieves a paginated list of all registered users.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "List of users retrieved successfully"),
		@ApiResponse(responseCode = "403", description = "Forbidden - Insufficient permissions")
	})
	public ResponseEntity<PaginatedUserResponse> getAllUsers(@RequestHeader("Authorization") String token, 
			@RequestParam(name = "page", defaultValue = "0") int page, 
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return ResponseEntity.ok(authService.getAllUsers(token, page, size));
	}
}