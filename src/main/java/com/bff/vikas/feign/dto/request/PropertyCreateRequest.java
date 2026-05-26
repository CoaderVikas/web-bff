package com.bff.vikas.feign.dto.request;

import java.math.BigDecimal;

import com.bff.vikas.enums.PropertyStatus;
import com.bff.vikas.enums.PropertyType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : PropertyCreateRequest
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 27, 2026
 * Version    : 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyCreateRequest {

	@NotBlank
	@Size(max = 100)
	private String title;

	@Size(max = 500)
	private String description;

	@NotBlank
	private String addressLine1;

	private String addressLine2;

	@NotBlank
	private String city;

	@NotBlank
	private String state;

	@NotBlank
	@Pattern(regexp = "\\d{6}", message = "Pincode must be 6 digits")
	private String pincode;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal rentAmount;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal securityDeposit;

	@NotNull
	private PropertyType propertyType;

	private PropertyStatus status;
}