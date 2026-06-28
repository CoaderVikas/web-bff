package com.bff.vikas.feign.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileResponse {
	// Identity
	private String fullName;
	private String username;
	private String email;

	// Authorization
	private String role;

	// Account status
	private Boolean enabled;
	 private String photoUrl;
	private Boolean accountNonLocked;
	
	//for status
	private String status;
	
	// Security info (optional / internal use)
	@JsonIgnore
	private Integer failedLoginAttempts;
	@JsonIgnore
	private LocalDateTime passwordLastUpdatedAt;
	
	private String message;
}