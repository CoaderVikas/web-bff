package com.bff.vikas.feign.dto.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Payload sent to Notification Service for a property verification event.
 *
 * recipientId : who should receive it (admin id on submit, owner id on decision)
 * event       : SUBMITTED / VERIFIED / REJECTED
 * message     : human-readable line shown in the bell
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationNotificationRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String recipientId;
	private String event; // SUBMITTED | VERIFIED | REJECTED
	private String propertyId;
	private String propertyName;
	private String ownerId; // property owner (for admin-side context)
	private String message;
	private String reason; // only for REJECTED
}