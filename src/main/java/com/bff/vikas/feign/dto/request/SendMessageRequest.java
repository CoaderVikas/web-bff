package com.bff.vikas.feign.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : SendMessageRequest
 * Description: Chat thread me naya message bhejne ka request.
 * Author     : Vikas Yadav
 * Created On : Jul 03, 2026
 * Version    : 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageRequest {
	// "owner" ya "tenant" — kaun bhej raha hai
	private String sender;
	private String text;
}
