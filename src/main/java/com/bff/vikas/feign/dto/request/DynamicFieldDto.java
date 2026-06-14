package com.bff.vikas.feign.dto.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : DynamicFieldDto
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
public class DynamicFieldDto implements Serializable{
	private Long id;
	private String label;
	private String value;
	private String type;
}
