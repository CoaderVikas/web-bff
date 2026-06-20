package com.bff.vikas.feign.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : TenantResponseDto
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 15, 2026
 * Version    : 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TenantResponseDto {

	@JsonIgnore
	private Long id;

	private String tenantCustomId;
	private String name;
	private String fatherName;
	private String contact;
	private String altContact;
	private String email;
	private String permanentAddress;
	private String currentAddress;
	private String identityDocType;

	/**
	 * Masked identity doc — shows only last 4 chars (e.g., "XXXX-XXXX-1234")
	 */
	private String identityDocNumber;

	private String panNumber;
	private String photoUrl;
	private Double globalRating;

	/**
	 * Who registered this tenant — null if self-registered
	 */
	private String registeredByOwnerId;

	/**
	 * Complete stay history across ALL owners. Each entry includes the ownerUserId
	 * and their feedback for that stay.
	 */
	private List<AllocationHistoryDetails> allocations;

	/**
	 * All feedback entries from all owners. Useful for the "Tenant History" tab in
	 * a new owner's view.
	 */
	// private List<FeedbackResponseDto> feedbackHistory;
}