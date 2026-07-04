package com.bff.vikas.feign.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : ChatMessageResponseDto
 * Description: Frontend ke exact shape me chat message ({id, sender, text}).
 * Author     : Vikas Yadav
 * Created On : Jul 03, 2026
 * Version    : 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageResponseDto {
	private String id;
	private String sender; // "owner" | "tenant"
	private String text;
}
