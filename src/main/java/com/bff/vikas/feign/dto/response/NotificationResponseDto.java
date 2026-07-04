package com.bff.vikas.feign.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : NotificationResponseDto
 * Description: Frontend (UserTopbar.jsx) ke exact shape me notification.
 *              Nested tenant/property/room objects frontend ke hisaab se.
 * Author     : Vikas Yadav
 * Created On : Jul 03, 2026
 * Version    : 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDto {

	private String id;         // frontend string expect karta hai
	private String type;       // "BOOKING" | "CHAT"
	private String status;     // "pending" | "accepted"
	private Tenant tenant;
	private Property property;
	private Room room;         // sirf BOOKING ke liye
	private String message;    // sirf CHAT ke liye
	private String createdAt;  // ISO string

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Tenant {
		private String name;
		private String contact;
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Property {
		private String name;
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Room {
		private String number;
		private String bedName;
	}
}
