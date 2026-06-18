package com.bff.vikas.feign.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : ActiveAllocationDetails
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 15, 2026
 * Version    : 1.0
 */

@Data
@Builder
@NoArgsConstructor  
@AllArgsConstructor
public class ActiveAllocationDetails {
	private int floorNo;
	private String roomNumber;
	private String bedName;
	private String propertyType;
	private String status; // "Occupied"
	private TenantMiniInfo tenantInfo;
}
