package com.bff.vikas.feign.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : PropertyAllocationResponseDto
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 15, 2026
 * Version    : 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyAllocationResponseDto {
	private String propertyId;
    private List<ActiveAllocationDetails> activeAllocations;
    private List<AllocationHistoryDetails> allocationHistory;
}
