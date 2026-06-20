package com.bff.vikas.feign.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : TenantRegistrationDto
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 15, 2026
 * Version    : 1.0
 */

@Data
public class TenantRegistrationDto {

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Father name is required")
    @Size(min = 2, max = 100, message = "Father name must be between 2 and 100 characters")
    private String fatherName;

    @NotBlank(message = "Primary contact number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Contact must be a valid 10-digit Indian mobile number")
    private String contact;

    @Pattern(regexp = "^$|^[6-9]\\d{9}$", message = "Alternate contact must be a valid 10-digit mobile number")
    private String altContact;

    @NotBlank(message = "Email address is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Permanent address is required")
    @Size(max = 500)
    private String permanentAddress;

    @NotBlank(message = "Current address is required")
    @Size(max = 500)
    private String currentAddress;

    @NotBlank(message = "Identity document type is required")
    private String identityDocType;

    @NotBlank(message = "Identity document number is required")
    @Size(min = 5, max = 50)
    private String identityDocNumber;

    @Pattern(regexp = "^$|^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN format (e.g., ABCDE1234F)")
    private String panNumber;
}