package com.bff.vikas.feign.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Class      : UserProfileResponse
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Mar 1, 2026
 * Version    : 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
	// Identity
	private String fullName;
	private String username;
	private String email;

	// Authorization
	private String role;

	// Account status
	private Boolean enabled;
	private Boolean accountNonLocked;

	// Security info (optional / internal use)
	private Integer failedLoginAttempts;
	private LocalDateTime passwordLastUpdatedAt;
	
	//for status
	private String status;
}