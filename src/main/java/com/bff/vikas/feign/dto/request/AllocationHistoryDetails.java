package com.bff.vikas.feign.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : AllocationHistoryDetails
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 15, 2026
 * Version    : 1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllocationHistoryDetails {

	private Long allocationId;
	private String propertyId;
	private String propertyType;
	private int floorNo;
	private String roomNumber;
	private String bedName;
	private String tenantName;
	private String status;
	private String checkInDate;
	private String checkOutDate;

	/**
	 * Owner who allocated this tenant to this property. Shown in history so future
	 * owner knows the chain of custody.
	 */
	private String ownerUserId;

	/**
	 * Feedback given by that owner for this specific stay. NULL if owner hasn't
	 * submitted feedback yet.
	 */
	// private FeedbackResponseDto ownerFeedback;
}