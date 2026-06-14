package com.bff.vikas.feign.dto.request;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : MetaConfigDto
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 14, 2026
 * Version    : 1.0
 */

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetaConfigDto implements Serializable {
	private String customType;
	private String addressLine;
	private String city;
	private String pincode;
	private List<DynamicFieldDto> dynamicFields;
}