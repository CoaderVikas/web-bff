package com.bff.vikas.feign.dto.request;

import java.math.BigDecimal;

import com.bff.vikas.enums.PropertyStatus;
import com.bff.vikas.enums.PropertyType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : PropertyUpdateRequest
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 27, 2026
 * Version    : 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyUpdateRequest {
	@Size(max = 100)
	private String title;

	@Size(max = 500)
	private String description;

	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;

	@Pattern(regexp = "\\d{6}")
	private String pincode;

	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal rentAmount;

	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal securityDeposit;

	private PropertyType propertyType;
	private PropertyStatus status;

}
