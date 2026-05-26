package com.bff.vikas.feign.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.bff.vikas.enums.PropertyStatus;
import com.bff.vikas.enums.PropertyType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : PropertyResponse
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 27, 2026
 * Version    : 1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PropertyResponse {

	private UUID id;
	private String propertyNumber;
	private String title;
	private String description;

	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String pincode;

	private BigDecimal rentAmount;
	private BigDecimal securityDeposit;

	private PropertyType propertyType;
	private PropertyStatus status;

	private Boolean isVerified;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private List<String> images;
}
