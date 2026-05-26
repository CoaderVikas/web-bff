package com.bff.vikas.feign.dto.request;

import java.math.BigDecimal;

import com.bff.vikas.enums.PropertyStatus;
import com.bff.vikas.enums.PropertyType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : PropertySearchRequest
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 27, 2026
 * Version    : 1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PropertySearchRequest {

	
	@Min(value = 0, message = "Page index cannot be negative")
	private int page = 0;

	@Min(value = 1, message = "Size must be at least 1")
	@Max(value = 100, message = "Size cannot exceed 100")
	private int size = 10;

	private String sortBy = "createdAt"; // will be whitelisted
	private String sortDirection = "DESC"; // DESC | ASC

	
	private String city;
	private String state;
	private String pincode;

	private PropertyType propertyType;
	private PropertyStatus status;

	
	private BigDecimal minRent;
	private BigDecimal maxRent;

	
	private String keyword;

	public String getSortDirectionSafe() {
		if (sortDirection == null)
			return "DESC";
		return sortDirection.equalsIgnoreCase("ASC") ? "ASC" : "DESC";
	}

	public String getSortBySafe() {
		if (sortBy == null || sortBy.isBlank())
			return "createdDate";
		return sortBy;
	}

	public int getPageSafe() {
		return Math.max(page, 0);
	}

	public int getSizeSafe() {
		return (size <= 0 || size > 100) ? 10 : size;
	}
}