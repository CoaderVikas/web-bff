package com.bff.vikas.feign.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : TenantRegistrationDto
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 15, 2026
 * Version    : 1.0
 */

@Data
@Builder
@NoArgsConstructor  // 🔥 Jackson के लिए सबसे ज़रूरी (खाली कंस्ट्रक्टर)
@AllArgsConstructor
public class TenantRegistrationDto {
	private String name;
	private String fatherName;
	private String contact;
	private String altContact;
	private String email;
	private String permanentAddress;
	private String currentAddress;
	private String identityDocType;
	private String identityDocNumber;
	private String panNumber;
	private String photoUrl;
}
