package com.bff.vikas.feign.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Class      : TenantUpdateRequestDto
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 18, 2026
 * Version    : 1.0
 */
@Data
public class TenantUpdateRequestDto {

	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;

	@Size(min = 2, max = 100, message = "Father name must be between 2 and 100 characters")
	private String fatherName;

	@Pattern(regexp = "^$|^[6-9]\\d{9}$", message = "Contact number must be a valid 10-digit Indian mobile number")
	private String contact;

	@Pattern(regexp = "^$|^[6-9]\\d{9}$", message = "Alternate contact number must be a valid 10-digit mobile number")
	private String altContact;

	@Email(message = "Please provide a valid email address")
	private String email;

	@Size(max = 500, message = "Permanent address cannot exceed 500 characters")
	private String permanentAddress;

	@Size(max = 500, message = "Current address cannot exceed 500 characters")
	private String currentAddress;

	private String identityDocType; // E.g., AADHAAR, PAN, VOTER_ID

	@Size(min = 5, max = 50, message = "Identity document number length is invalid")
	private String identityDocNumber;

	// Matches standard Indian PAN format only if a value is provided
	@Pattern(regexp = "^$|^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Please provide a valid PAN card number format")
	private String panNumber;

	// Note: 'photoUrl' text field is optional here, as main image uploading is
	// handled via MultipartFile
	private String photoUrl;
}
