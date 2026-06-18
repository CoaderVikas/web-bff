package com.bff.vikas.feign.dto.request;


import java.time.LocalDate;

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
	private String propertyId;
	private String propertyType;
	private Integer floorNo;
	private String roomNumber;
	private String bedName;
	private String contact;
	private Double advancePaid;
	private LocalDate checkInDate;
}
