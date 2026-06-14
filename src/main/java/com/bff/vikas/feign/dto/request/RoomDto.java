package com.bff.vikas.feign.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : RoomDto
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
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class RoomDto implements Serializable{

	private String roomNumber;
	private String roomType;
	private Integer sharing;
	private Integer occupied;
	private String status;
	private BigDecimal price;
	private Boolean isAC;
	private Boolean isAvailable;
}
