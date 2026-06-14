package com.bff.vikas.feign.dto.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : FloorDto
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
public class FloorDto implements Serializable {
	private Integer floorNo;
	private List<RoomDto> rooms;
}