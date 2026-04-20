package com.bff.vikas.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.bff.vikas.config.FeignConfig;
import com.bff.vikas.feign.dto.LoginRequest;
import com.bff.vikas.feign.dto.LoginResponse;
import com.bff.vikas.feign.dto.PasswordResetRequest;
import com.bff.vikas.feign.dto.PasswordResetResponse;
import com.bff.vikas.feign.dto.RefreshRequest;
import com.bff.vikas.feign.dto.RegisterRequest;
import com.bff.vikas.feign.dto.UpdateProfileRequest;
import com.bff.vikas.feign.dto.UserProfileResponse;

import jakarta.validation.Valid;



/**
 * Class      : AuthServiceFeignClient
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 15, 2026
 * Version    : 1.0
 */

@FeignClient(name = "AUTH-SERVICE",path = "/rent-hub/auth",configuration = FeignConfig.class)
public interface AuthServiceFeignClient {
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	@GetMapping("/profile/me")
	UserProfileResponse getMyProfile(@RequestHeader("Authorization") String token);

	/**
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@PutMapping("/profile/me")
	UserProfileResponse updateProfile(@RequestHeader("Authorization") String token,@RequestBody UpdateProfileRequest request);

	/**
	 * 
	 * @param username
	 * @return
	 */
	@PostMapping("/password/forgot")
	String generateOtp(@RequestParam("username") String username);

	/**
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/password/reset")
	PasswordResetResponse resetPassword(@RequestBody PasswordResetRequest request);
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/auth/register")
	LoginResponse register(@RequestBody RegisterRequest request);

	/**
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/auth/login")
	LoginResponse login(@RequestBody LoginRequest request);
	
	/**
	 * 
	 * @param token
	 * @param id
	 * @return
	 */
	@PutMapping("/rent-hub/auth/admin/users/{id}/lock")
	String lockUser(@RequestHeader("Authorization") String token, @PathVariable("id") Long id);

	/**
	 * 
	 * @param token
	 * @param id
	 * @return
	 */
	@PutMapping("/rent-hub/auth/admin/users/{id}/unlock")
	String unlockUser(@RequestHeader("Authorization") String token, @PathVariable("id") Long id);

	/**
	 * 
	 * @param token
	 * @param id
	 * @param enabled
	 * @return
	 */
	@PutMapping("/rent-hub/auth/admin/users/{id}/status")
	String updateUserStatus(@RequestHeader("Authorization") String token, @PathVariable("id") Long id,
			@RequestParam("enabled") boolean enabled);

	/**
	 * 
	 * @param token
	 * @param id
	 * @param role
	 * @return
	 */
	@PutMapping("/rent-hub/auth/admin/users/{id}/role")
	String updateUserRole(@RequestHeader("Authorization") String token, @PathVariable("id") Long id,
			@RequestParam("role") String role);

	/**
	 * 
	 */
	/*@GetMapping("/rent-hub/auth/admin/users")
	/*Page<AdminUserResponse> getAllUsers(@RequestHeader("Authorization") String token, @RequestParam("page") int page,
			@RequestParam("size") int size);*/
	
	@PostMapping("/jwt/logout")
	public String logout(@RequestHeader("Authorization")String Token,@Valid @RequestBody RefreshRequest request);
}
