package com.bff.vikas.feign.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : VacateRequestDto
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 17, 2026
 * Version    : 1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacateRequestDto {
	private String propertyId;
	private Integer floorNo;
	private String roomNumber;
	private String bedName;
	private String propertyType;
}