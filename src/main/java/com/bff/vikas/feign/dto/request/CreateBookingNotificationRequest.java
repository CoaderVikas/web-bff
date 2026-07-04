package com.bff.vikas.feign.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : CreateBookingNotificationRequest
 * Description: Booking hone par owner ke liye notification banane ka request.
 * Author     : Vikas Yadav
 * Created On : Jul 03, 2026
 * Version    : 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingNotificationRequest {
	private String ownerId;
	private String tenantId;
	private String tenantName;
	private String tenantContact;
	private String propertyId;
	private String propertyName;
	private String roomNumber;
	private String bedName;
}
