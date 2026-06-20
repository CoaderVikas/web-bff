package com.bff.vikas.feign.dto.request;


import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Class      : AllocationRequestDto
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 15, 2026
 * Version    : 1.0
 */
@Data
public class AllocationRequestDto {

	@NotBlank(message = "Property ID is required")
	private String propertyId;

	@NotBlank(message = "Property type is required")
	private String propertyType;

	@NotNull(message = "Floor number is required")
	private Integer floorNo;

	@NotBlank(message = "Room number is required")
	private String roomNumber;

	// Optional: only required for PG/Hostel bed-sharing
	private String bedName;

	@NotBlank(message = "Tenant ID is required to identify tenant")
	private String tenantId;

	private Double advancePaid;

	private LocalDate checkInDate;
}