package com.bff.vikas.feign.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Class      : PhoneLoginRequestDto
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jul 11, 2026
 * Version    : 1.0
 */

@Data
public class PhoneLoginRequestDto {
	@NotBlank(message = "Firebase Token is required")
    private String firebaseIdToken;
}
