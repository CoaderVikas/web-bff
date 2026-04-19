package com.bff.vikas.feign.fallback;

import com.bff.vikas.feign.client.AuthServiceFeignClient;
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
 * Created On : Apr 19, 2026
 * Version    : 1.0
 */

public class AuthServiceFallback implements AuthServiceFeignClient{

	@Override
	public UserProfileResponse getMyProfile(String token) {
		return UserProfileResponse.builder().status("SERVICE_UNAVAILABLE").build();
	}

	@Override
	public UserProfileResponse updateProfile(String token, UpdateProfileRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateOtp(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PasswordResetResponse resetPassword(PasswordResetRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginResponse register(RegisterRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String lockUser(String token, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String unlockUser(String token, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateUserStatus(String token, Long id, boolean enabled) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateUserRole(String token, Long id, String role) {
		// TODO Auto-generated method stub
		return null;
	}

}
