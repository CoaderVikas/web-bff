package com.bff.vikas.feign.dto.request;

import java.io.Serializable;

import lombok.Data;

/**
 * Class : PropertyUpdateRequestDTO Description: [Add brief description here]
 * Author : Vikas Yadav Created On : Jun 16, 2026 Version : 1.0
 */
@Data
public class PropertyUpdateRequestDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String mainPropertyType;
	private String action;
	private Integer floorNo;
	private String roomNumber;
	private String allocatedToId;
	private String allocatedToType;
}
