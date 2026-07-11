package com.bff.vikas.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.bff.vikas.config.FeignConfig;
import com.bff.vikas.feign.dto.request.ChangePasswordRequest;
import com.bff.vikas.feign.dto.request.GoogleAuthRequest;
import com.bff.vikas.feign.dto.request.LoginRequest;
import com.bff.vikas.feign.dto.request.PhoneLoginRequestDto;
import com.bff.vikas.feign.dto.request.PhoneResetRequestDto;
import com.bff.vikas.feign.dto.request.RefreshRequest;
import com.bff.vikas.feign.dto.request.RegisterRequest;
import com.bff.vikas.feign.dto.request.UpdateProfileRequest;
import com.bff.vikas.feign.dto.response.ChangePasswordResponse;
import com.bff.vikas.feign.dto.response.GoogleAuthResponse;
import com.bff.vikas.feign.dto.response.LoginResponse;
import com.bff.vikas.feign.dto.response.PaginatedUserResponse;
import com.bff.vikas.feign.dto.response.PasswordResetRequest;
import com.bff.vikas.feign.dto.response.PasswordResetResponse;
import com.bff.vikas.feign.dto.response.UserProfileResponse;

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
	 * @param Token
	 * @param request
	 * @return
	 */
	@PostMapping("/jwt/logout")
	public String logout(@RequestHeader("Authorization")String Token,@Valid @RequestBody RefreshRequest request);
	
	/**
	 * 
	 * @param Token
	 * @param request
	 * @return
	 */
	@PostMapping("/jwt/refresh")
	public LoginResponse refreshToken(@RequestBody RefreshRequest request);
	
	/**
	 * 
	 * @param request
	 * @param authentication
	 * @return
	 */
	@PutMapping(value = "/jwt/change")
	public ChangePasswordResponse changePassword(@RequestBody ChangePasswordRequest request,@RequestHeader("Authorization") String token);	/**
	 * 
	 */
	
	/**
	 * 
	 * @param token
	 * @param id
	 * @return
	 */
	@PutMapping("/admin/users/{id}/lock")
	String lockUser(@RequestHeader("Authorization") String token, @PathVariable("id") Long id);

	/**
	 * 
	 * @param token
	 * @param id
	 * @return
	 */
	@PutMapping("/admin/users/{id}/unlock")
	String unlockUser(@RequestHeader("Authorization") String token, @PathVariable("id") Long id);

	/**
	 * 
	 * @param token
	 * @param id
	 * @param enabled
	 * @return
	 */
	@PutMapping("/admin/users/{id}/status")
	String updateUserStatus(@RequestHeader("Authorization") String token, @PathVariable("id") Long id,@RequestParam("enabled") boolean enabled);

	/**
	 * 
	 * @param token
	 * @param id
	 * @param role
	 * @return
	 */
	@PutMapping("/admin/users/{id}/role")
	String updateUserRole(@RequestHeader("Authorization") String token, @PathVariable("id") Long id,@RequestParam("role") String role);

	/**
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/admin/users")
	public PaginatedUserResponse getAllUsers(@RequestHeader("Authorization")String token,@RequestParam(name = "page", defaultValue = "0") int page,@RequestParam(name = "size", defaultValue = "10") int size);
	
	/**
	 * 
	 * @param authentication
	 * @param file
	 * @return
	 */
	@PutMapping(value = "/profile/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	UserProfileResponse updateUserPhoto(@RequestPart("file") MultipartFile file);
	
	@GetMapping("/profile/getImage")
	Resource getUserImage();
	
	@GetMapping("/profile/getImage/{username}")
	public Resource getUserImageByUserId(@PathVariable("username") String username);
	
	
	@PostMapping("/auth/google")
	GoogleAuthResponse googleAuth(@RequestBody GoogleAuthRequest request);
	
    @PostMapping(value = "/phone/login-otp", consumes = MediaType.APPLICATION_JSON_VALUE)
    LoginResponse phoneLoginOtp(@RequestBody PhoneLoginRequestDto request);

    @PostMapping(value = "/phone/reset", consumes = MediaType.APPLICATION_JSON_VALUE)
    PasswordResetResponse phoneReset(@RequestBody PhoneResetRequestDto request);
}
