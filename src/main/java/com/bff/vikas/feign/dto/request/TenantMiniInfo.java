package com.bff.vikas.feign.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : TenantMiniInfo
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 15, 2026
 * Version    : 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenantMiniInfo {
	private String tenantCustomId;
	private String name;
	private String contact;
	private String checkInDate; // Check-in
	private String checkoutDate; // "—"
}